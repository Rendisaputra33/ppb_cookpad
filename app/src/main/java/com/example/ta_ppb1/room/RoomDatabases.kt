package com.example.ta_ppb1.room

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ta_ppb1.entity.Recipe
import com.example.ta_ppb1.entity.RecipeWithAuthor
import com.example.ta_ppb1.entity.User
import com.example.ta_ppb1.repository.RecipeRepository
import com.example.ta_ppb1.repository.UserRepository
import com.example.ta_ppb1.utils.ioThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

@Database(entities = [User::class, Recipe::class], views = [RecipeWithAuthor::class], version = 1)
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
                @SuppressLint("SimpleDateFormat")
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        val recipeRepository = invoke(context).recipeRepository()
                        val userRepository = invoke(context).userRepository()

                        CoroutineScope(Dispatchers.IO).launch {
                            val time = Calendar.getInstance().time
                            val formatter = SimpleDateFormat("dd-MM-yyyy")
                            val current = formatter.format(time)

                            userRepository.insertAll(User("dapurkita", "dapurkita", "Dapur Kita"))
                            recipeRepository.insertAll(
                                Recipe(
                                    2,
                                    "Nasi Padang Rendi",
                                    "",
                                    "hjdjhf",
                                    "jhsdhhdjs",
                                    "",
                                    current
                                )
                            )
                            recipeRepository.insertAll(
                                Recipe(2, "Nasi Padang @", "", "hjdjhf", "jhsdhhdjs", "", current)
                            )
                        }
                    }
                }
            }
        }
    }
}