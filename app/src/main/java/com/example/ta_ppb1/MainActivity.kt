package com.example.ta_ppb1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ta_ppb1.databinding.ActivityMainBinding
import com.example.ta_ppb1.room.RoomDatabases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    /**
     * create instance of Binder MainActivity
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * instance of database
     */
    private val database by lazy { RoomDatabases(this) }

    /**
     * @param savedInstanceState instance of Bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.buttonLogin.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val user = database.userRepository().findByEmail(binding.UsernameLogin.text.toString())
            }
        }
    }
}