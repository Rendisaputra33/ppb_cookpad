package com.example.ta_ppb1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ta_ppb1.constant.Action
import com.example.ta_ppb1.databinding.ActivityMainBinding
import com.example.ta_ppb1.room.RoomDatabase

class MainActivity : AppCompatActivity() {
    /**
     * create instance of Binder MainActivity
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * instance of database
     */
    val database by lazy { RoomDatabase(this) }

    /**
     * @param savedInstanceState instance of Bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }
}