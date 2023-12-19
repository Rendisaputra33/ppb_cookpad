package com.example.ta_ppb1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ta_ppb1.entity.Recipe
import com.example.ta_ppb1.entity.User
import com.example.ta_ppb1.repository.RecipeRepository
import com.example.ta_ppb1.repository.UserRepository

@Database(entities = [User::class, Recipe::class], version = 1)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun recipeRepository(): RecipeRepository
    abstract fun userRepository(): UserRepository

    companion object {
        private val LOCK = Any()

        private var instace: RoomDatabase? = null

        operator fun invoke(ctx: Context) = instace ?: synchronized(LOCK) {
            instace ?: init(ctx).also {
                instace = it
            }
        }

        private fun init(ctx: Context) = Room.databaseBuilder(
            ctx.applicationContext,
            RoomDatabase::class.java,
            "recipe-db.db"
        ).build()
    }
}