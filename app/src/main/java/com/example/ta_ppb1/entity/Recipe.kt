package com.example.ta_ppb1.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    val author: Int,
    val name: String,
    val description: String,
    val condiment: String,
    @ColumnInfo(name = "steps_cooking")
    val stepsCooking: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
