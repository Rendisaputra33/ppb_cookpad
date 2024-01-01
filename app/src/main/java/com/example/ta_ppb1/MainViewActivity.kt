package com.example.ta_ppb1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ta_ppb1.adapter.RecipesAdapter
import com.example.ta_ppb1.databinding.ActivityMainViewBinding
import com.example.ta_ppb1.entity.RecipeWithAuthor
import com.example.ta_ppb1.room.RoomDatabases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewActivity : AppCompatActivity() {

    /**
     * binding ActivityMainViewBinding
     */
    private lateinit var binding: ActivityMainViewBinding

    private lateinit var recipeAdapter: RecipesAdapter

    /**
     * inisialisasi database
     */
    private val database by lazy { RoomDatabases(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerEvents()
        setUpData()

        val id =
            this.getSharedPreferences("LOCAL_STORAGE", Context.MODE_PRIVATE).getInt("userId", 1)
        Log.i("INFO ID : ", id.toString())
    }

    private fun setUpData() {
        CoroutineScope(Dispatchers.IO).launch {
            val recipes = database.recipeRepository().getAll()

            withContext(Dispatchers.Main) {
                recipeAdapter.dispatch(recipes.toCollection(ArrayList<RecipeWithAuthor>()))
            }
        }
    }

    private fun registerEvents() {
        val intent = Intent(this, EditResep::class.java)

        recipeAdapter = RecipesAdapter(arrayListOf(), object : RecipesAdapter.Events {
            override fun onClick(recipe: RecipeWithAuthor) {
                intent.putExtra("recipe_id", recipe.id)
                startActivity(intent)  
            }
        })

        binding.listContainer.apply {
            layoutManager = GridLayoutManager(binding.gridContainer.context, 2)
            adapter = recipeAdapter
        }

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddRecipe::class.java)
            startActivity(intent)
        }

        binding.me.setOnClickListener {
            val intent = Intent(this, MyRecipe::class.java)
            startActivity(intent)
        }

        binding.searchInput.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val query = binding.searchInput.text.toString()

                Log.i("info", query)

                CoroutineScope(Dispatchers.IO).launch {
                    val recipes: Array<RecipeWithAuthor> = if (query != "") {
                        database.recipeRepository().getByQuery("%$query%")
                    } else {
                        database.recipeRepository().getAll()
                    }

                    withContext(Dispatchers.Main) {
                        recipeAdapter.dispatch(recipes.toCollection(ArrayList<RecipeWithAuthor>()))
                    }
                }
            }
        }
    }
}