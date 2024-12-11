package com.dicoding.foodtopia.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FavoriteRecipe::class], version = 1)
@TypeConverters(Converters::class)
abstract class FavoriteRecipeDatabase : RoomDatabase() {
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteRecipeDatabase? = null

        fun getDatabase(context: Context): FavoriteRecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteRecipeDatabase::class.java,
                    "favorite_recipe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
