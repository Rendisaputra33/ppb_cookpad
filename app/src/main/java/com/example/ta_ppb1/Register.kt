package com.example.ta_ppb1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ta_ppb1.databinding.ActivityRegisterBinding
import com.example.ta_ppb1.entity.User
import com.example.ta_ppb1.room.RoomDatabases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val database by lazy { RoomDatabases(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val id = Storage<Int>().get(this, General.USER_ID, 0)
//
//        Log.i("INDO : ", id.toString())
//
//        if (id != null && id > 0) {
//            startActivity(Intent(this, MainViewActivity::class.java))
//        } val id = Storage<Int>().get(this, General.USER_ID, 0)
//
//        Log.i("INDO : ", id.toString())
//
//        if (id != null && id > 0) {
//            startActivity(Intent(this, MainViewActivity::class.java))
//        }

        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val username = binding.UsernameRegister.text.toString()
            val password = binding.PasswordRegister.text.toString()
            val name = binding.YournameRegister.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val user = User(username, password, name)
                database.userRepository().insertAll(user)
                startActivity(intent)
            }
        }

        binding.toLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}