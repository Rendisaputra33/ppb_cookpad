package com.example.ta_ppb1.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ta_ppb1.entity.User

@Dao
interface UserRepository {
    @Query("SELECT * FROM users")
    suspend fun all(): Array<User>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): User?

    @Insert
    suspend fun insertAll(vararg users: User)
}