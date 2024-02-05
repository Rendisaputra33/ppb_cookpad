package com.example.ta_ppb1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ta_ppb1.constant.General
import com.example.ta_ppb1.databinding.ActivityRegisterBinding
import com.example.ta_ppb1.entity.User
import com.example.ta_ppb1.room.RoomDatabases
import com.example.ta_ppb1.utils.Storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val database by lazy { RoomDatabases(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = Storage<Int>().get(this, General.USER_ID, 0)

        if (id != null && id > 0) {
            startActivity(Intent(this, MainViewActivity::class.java))
        }

        binding.buttonRegister.setOnClickListener {
            val parentObject = this
            val intent = Intent(this, MainActivity::class.java)
            val username = binding.UsernameRegister.text.toString()
            val password = binding.PasswordRegister.text.toString()
            val name = binding.YournameRegister.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                // get user by username
                val userExist = database.userRepository().findByEmail(username)
                if (userExist == null) {
                    val user = User(username, password, name)
                    database.userRepository().insertAll(user)
                    startActivity(intent)
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(parentObject, "Username telah terdaftar", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }

        binding.toLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
