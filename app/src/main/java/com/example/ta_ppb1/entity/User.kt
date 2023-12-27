package com.example.ta_ppb1.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val email: String,
    val password: String,
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
