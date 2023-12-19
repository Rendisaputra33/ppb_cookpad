package com.example.ta_ppb1.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ta_ppb1.entity.Recipe

@Dao
interface RecipeRepository {
    @Query("SELECT * FROM recipes")
    fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): Recipe

    @Insert
    fun insertAll(vararg users: Recipe)

    @Delete
    fun delete(user: Recipe)
}