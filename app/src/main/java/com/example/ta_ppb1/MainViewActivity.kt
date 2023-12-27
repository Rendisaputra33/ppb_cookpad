package com.example.ta_ppb1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ta_ppb1.databinding.ActivityMainViewBinding
import com.example.ta_ppb1.room.RoomDatabases

class MainViewActivity : AppCompatActivity() {

    /**
     * binding ActivityMainViewBinding
     */
    private lateinit var binding: ActivityMainViewBinding

    /**
     * inisialisasi database
     */
    private val database by lazy { RoomDatabases(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.registerEvents()
    }

    private fun registerEvents() {

    }
}