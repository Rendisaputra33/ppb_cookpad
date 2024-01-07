package com.example.ta_ppb1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ta_ppb1.adapter.MyRecipeAdapter
import com.example.ta_ppb1.constant.General
import com.example.ta_ppb1.databinding.ActivityMyRecipeBinding
import com.example.ta_ppb1.entity.Recipe
import com.example.ta_ppb1.entity.RecipeWithAuthor
import com.example.ta_ppb1.room.RoomDatabases
import com.example.ta_ppb1.utils.Storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class MyRecipe : AppCompatActivity() {
    private lateinit var binding: ActivityMyRecipeBinding

    private val database by lazy { RoomDatabases(this) }

    private lateinit var adapterMyRecipe: MyRecipeAdapter

    private var iduUser by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intent = Intent(this, MainViewActivity::class.java)
            startActivity(intent)
        }

        setupAdapter()

        iduUser = Storage<Int>().get(this, General.USER_ID, 2)!!

        setupData()
    }

    private fun setupAdapter() {
        val intentEdit = Intent(this, EditResep::class.java)
        val parentObject = this

        adapterMyRecipe = MyRecipeAdapter(arrayListOf(), object : MyRecipeAdapter.Events {
            override fun onEdit(recipe: RecipeWithAuthor) {
                intentEdit.putExtra("recipe_id", recipe.id)
                startActivity(intentEdit)
            }

            override fun onDelete(recipe: RecipeWithAuthor) {
                val entity = Recipe(
                    author = recipe.author,
                    description = recipe.description,
                    name = recipe.name,
                    condiment = recipe.condiment,
                    stepsCooking = recipe.stepsCooking,
                    imageUrl = recipe.imageUrl,
                    date = recipe.date,
                )

                entity.id = recipe.id

                AlertDialog.Builder(parentObject)
                    .setTitle("Yakin ingin menghapus data?")
                    .setPositiveButton("Ya") { _, _ ->
                        CoroutineScope(Dispatchers.IO).launch {
                            database.recipeRepository().delete(entity)

                            val recipes = database.recipeRepository().getAllMyRecipe(iduUser)

                            withContext(Dispatchers.Main) {
                                adapterMyRecipe.dispatch(recipes.toCollection(ArrayList()))
                            }
                        }
                    }.setNegativeButton("Tidak") { _, _ ->

                    }.create()
                    .show()
            }
        })

        binding.scrollContainer.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapterMyRecipe
        }
    }

    private fun setupData() {
        CoroutineScope(Dispatchers.IO).launch {
            val recipes = database.recipeRepository().getAllMyRecipe(iduUser)
            withContext(Dispatchers.Main) {
                adapterMyRecipe.dispatch(recipes.toCollection(ArrayList()))
            }
        }
    }
}