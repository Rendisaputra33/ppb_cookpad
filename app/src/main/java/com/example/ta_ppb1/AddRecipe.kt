package com.example.ta_ppb1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ta_ppb1.databinding.ActivityTambahresepBinding
import com.example.ta_ppb1.room.RoomDatabases

class AddRecipe : AppCompatActivity() {
    private lateinit var binding: ActivityTambahresepBinding

    val database by lazy { RoomDatabases(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahresepBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_tambahresep)

        binding.unggah.setOnClickListener {

        }
    }
}