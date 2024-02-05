package com.example.ta_ppb1.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "users", indices = [Index(value = ["email"], unique = true)])
data class User(
    val email: String,
    val password: String,
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
