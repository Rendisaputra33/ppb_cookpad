package com.example.ta_ppb1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ta_ppb1.databinding.ActivityMainBinding
import com.example.ta_ppb1.room.RoomDatabases
import com.example.ta_ppb1.utils.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        CoroutineScope(Dispatchers.IO).launch {
            val users = database.userRepository().all()

            users.map {
                Log.i("user : ", "${it.id} - ${it.name}")
            }
        }

        binding.buttonLogin.setOnClickListener {
            val parentObject = this
            val intent = Intent(parentObject, MainViewActivity::class.java)
            val username = binding.UsernameLogin.text.toString()
            val password = binding.PasswordLogin.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val user = database.userRepository().findByEmail(username)
                if (user?.password == password) {
                    withContext(Dispatchers.Main) {
                        storage<Int>().save(parentObject, user.id)
                        startActivity(intent)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(parentObject, "username/password salah", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }
}