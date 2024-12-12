package com.dicoding.foodtopia.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT * FROM favorite_recipe")
    fun getFavoriteRecipes(): LiveData<List<FavoriteRecipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: FavoriteRecipe)

    @Query("DELETE FROM favorite_recipe WHERE id = :recipeId")
    suspend fun deleteById(recipeId: String)

    @Query("SELECT * FROM favorite_recipe WHERE id = :recipeId LIMIT 1")
    suspend fun getRecipeById(recipeId: Int): FavoriteRecipe?
}
