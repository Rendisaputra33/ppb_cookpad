package com.example.ta_ppb1.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ta_ppb1.entity.User

@Dao
interface UserRepository {
    @Query("SELECT * FROM users WHERE email = :email")
    fun findByEmail(email: String): User

    @Insert
    fun insert(data: User)
}