package com.example.ta_ppb1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ta_ppb1.databinding.ActivityMainBinding
import com.example.ta_ppb1.databinding.ActivityTambahresepBinding
import com.example.ta_ppb1.room.RoomDatabase

class AddRecipe : AppCompatActivity() {
    private lateinit var binding: ActivityTambahresepBinding

    val database by lazy{RoomDatabase(this)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahresepBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_tambahresep)

        binding.unggah.setOnClickListener {

        }
    }
}