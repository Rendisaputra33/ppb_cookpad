package com.example.ta_ppb1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ta_ppb1.databinding.ActivityEditresepBinding
import com.example.ta_ppb1.entity.Recipe
import com.example.ta_ppb1.room.RoomDatabases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar

class EditResep : AppCompatActivity() {

    private lateinit var binding: ActivityEditresepBinding

    private val database by lazy { RoomDatabases(this) }

    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditresepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("recipe_id", 0)
        val intent = Intent(this, MyRecipe::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            recipe = database.recipeRepository().findById(id)

            withContext(Dispatchers.Main) {
                binding.editNama.setText(recipe.name)
                binding.editBahan.setText(recipe.condiment)
                binding.editCara.setText(recipe.stepsCooking)
            }
        }

        binding.edit.setOnClickListener {
            val nama = binding.editNama.text.toString()
            val bahan = binding.editBahan.text.toString()
            val cara = binding.editCara.text.toString()
            val time = Calendar.getInstance().time
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            val current = formatter.format(time)

            val newRecipe =
                Recipe(
                    recipe.author,
                    nama,
                    recipe.description,
                    bahan,
                    cara,
                    recipe.imageUrl,
                    current
                )
            newRecipe.id = recipe.id

            CoroutineScope(Dispatchers.IO).launch {
                database.recipeRepository().update(newRecipe)

                withContext(Dispatchers.Main) {
                    startActivity(intent)
                }
            }
        }

        binding.back.setOnClickListener {
            startActivity(intent)
        }
    }
}
