package com.example.ta_ppb1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ta_ppb1.databinding.ActivityMainBinding
import com.example.ta_ppb1.room.RoomDatabases

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
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, MainViewActivity::class.java)
            startActivity(intent)
//            CoroutineScope(Dispatchers.IO).launch {
//                val user = database.userRepository().findByEmail(binding.UsernameLogin.text.toString())
//            }
        }
    }
}