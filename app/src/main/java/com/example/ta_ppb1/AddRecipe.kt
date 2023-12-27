package com.example.ta_ppb1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ta_ppb1.databinding.ActivityMainBinding
import com.example.ta_ppb1.databinding.ActivityTambahresepBinding
import com.example.ta_ppb1.entity.Recipe
import com.example.ta_ppb1.room.RoomDatabases

class AddRecipe : AppCompatActivity() {
    private lateinit var binding: ActivityTambahresepBinding

    private val database by lazy {RoomDatabases(this)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahresepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.unggah.setOnClickListener {
        val foto = binding.editFoto.text.toString()

        val nama = binding.editNama.text.toString()

        val bahan = binding.editBahan.text.toString()

        val cara = binding.editCara.text.toString()
            val recipe =  Recipe (1, 1, nama,"", bahan, cara, foto)
        database.recipeRepository().insertAll(recipe)
    val itn = Intent (this,EditResep::class.java)
            startActivity(itn)
        }
    }
}