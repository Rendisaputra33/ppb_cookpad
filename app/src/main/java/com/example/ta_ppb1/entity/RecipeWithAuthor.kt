package com.example.ta_ppb1.entity

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    "SELECT users.name as author_name, recipes.* FROM recipes JOIN users ON recipes.author = users.id",
    "recipe_author_view"
)
data class RecipeWithAuthor(
    var id: Int,
    val author: Int,
    @ColumnInfo(name = "author_name")
    val authorName: String,
    val name: String,
    val description: String,
    val condiment: String,
    @ColumnInfo(name = "steps_cooking")
    val stepsCooking: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
) {
}
