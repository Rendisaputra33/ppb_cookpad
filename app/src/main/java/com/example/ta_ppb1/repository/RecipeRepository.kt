package com.example.ta_ppb1.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ta_ppb1.entity.Recipe
import com.example.ta_ppb1.entity.RecipeWithAuthor

@Dao
interface RecipeRepository {
    @Query("SELECT * FROM recipe_author_view")
    suspend fun getAll(): Array<RecipeWithAuthor>

    @Query("SELECT * FROM recipe_author_view WHERE author = :author")
    suspend fun getAllMyRecipe(author: Int): Array<RecipeWithAuthor>

    @Query("SELECT * FROM recipe_author_view WHERE name LIKE :query")
    suspend fun getByQuery(query: String): Array<RecipeWithAuthor>

    @Query("SELECT * FROM recipes WHERE id IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: Int): Recipe

    @Insert
    suspend fun insertAll(vararg users: Recipe)

    @Delete
    suspend fun delete(user: Recipe)

    @Update
    suspend fun update(recipe: Recipe)
}