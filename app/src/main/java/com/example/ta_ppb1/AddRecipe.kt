package com.example.ta_ppb1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ta_ppb1.databinding.ActivityTambahresepBinding
import com.example.ta_ppb1.entity.Recipe
import com.example.ta_ppb1.room.RoomDatabases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddRecipe : AppCompatActivity() {
    private lateinit var binding: ActivityTambahresepBinding

    val database by lazy { RoomDatabases(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahresepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.unggah.setOnClickListener {

            val title = binding.editNama.text.toString()
            val condiment = binding.editBahan.text.toString()
            val steps = binding.editCara.text.toString()
            val urlImage = binding.editFoto.text.toString()

            val recipe = Recipe(1, title, "", condiment, steps, urlImage)

            val parentObject = this

            val intent = Intent(this, MainViewActivity::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                database.recipeRepository().insertAll(recipe)
                
                withContext(Dispatchers.Main) {
                    Toast.makeText(parentObject, "Success menambah resep", Toast.LENGTH_LONG).show()
                }

                startActivity(intent)
            }
        }
    }
}