package com.example.ta_ppb1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ta_ppb1.entity.Recipe
import com.example.ta_ppb1.entity.User
import com.example.ta_ppb1.repository.RecipeRepository
import com.example.ta_ppb1.repository.UserRepository
import com.example.ta_ppb1.utils.ioThread

@Database(entities = [User::class, Recipe::class], version = 1)
abstract class RoomDatabases : RoomDatabase() {
    abstract fun recipeRepository(): RecipeRepository
    abstract fun userRepository(): UserRepository

    companion object {
        private val LOCK = Any()

        private var instace: RoomDatabases? = null

        operator fun invoke(ctx: Context) = instace ?: synchronized(LOCK) {
            instace ?: init(ctx).also {
                instace = it
            }
        }

        private fun init(ctx: Context) = Room.databaseBuilder(
            ctx.applicationContext,
            RoomDatabases::class.java,
            "recipe-db.db"
        ).addCallback(seed(context = ctx)).build()

        private fun seed(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        val recipeRepository = invoke(context).recipeRepository()
                        recipeRepository.insertAll(
                            Recipe(1, "Nasi Padang Rendi", "", "hjdjhf", "jhsdhhdjs", "")
                        )
                        recipeRepository.insertAll(
                            Recipe(1, "Nasi Padang @", "", "hjdjhf", "jhsdhhdjs", "")
                        )
                    }
                }
            }
        }
    }
}