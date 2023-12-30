package com.example.ta_ppb1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ta_ppb1.adapter.RecipesAdapter
import com.example.ta_ppb1.databinding.ActivityMainViewBinding
import com.example.ta_ppb1.entity.Recipe
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
    }

    private fun setUpData() {
        CoroutineScope(Dispatchers.IO).launch {
            val recipes = database.recipeRepository().getAll()

            withContext(Dispatchers.Main) {
                recipeAdapter.dispatch(recipes.toCollection(ArrayList<Recipe>()))
            }
        }
    }

    private fun registerEvents() {
        recipeAdapter = RecipesAdapter(arrayListOf())

        binding.listContainer.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = recipeAdapter
        }

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddRecipe::class.java)
            startActivity(intent)
        }
    }
}