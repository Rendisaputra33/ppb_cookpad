package com.example.ta_ppb1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ta_ppb1.databinding.ActivityEditresepBinding
import com.example.ta_ppb1.room.RoomDatabases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditResep : AppCompatActivity() {

    private lateinit var binding: ActivityEditresepBinding

    private val database by lazy { RoomDatabases(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditresepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("recipe_id", 0)

        CoroutineScope(Dispatchers.IO).launch {
            val recipe = database.recipeRepository().findById(id)

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
        }

        binding.back.setOnClickListener {
            val intent = Intent(this, MainViewActivity::class.java)
            startActivity(intent)
        }
    }
}
