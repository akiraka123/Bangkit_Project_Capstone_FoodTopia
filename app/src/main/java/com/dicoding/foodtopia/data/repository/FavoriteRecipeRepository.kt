package com.dicoding.foodtopia.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.foodtopia.data.database.FavoriteRecipe
import com.dicoding.foodtopia.data.database.FavoriteRecipeDao

class FavoriteRecipeRepository(private val favoriteRecipeDao: FavoriteRecipeDao) {

    fun getFavoriteRecipes(): LiveData<List<FavoriteRecipe>> {
        return favoriteRecipeDao.getFavoriteRecipes()
    }

    suspend fun addToFavorites(recipe: FavoriteRecipe) {
        favoriteRecipeDao.insert(recipe)
    }

    suspend fun deleteRecipeById(recipeId: String) {
        favoriteRecipeDao.deleteById(recipeId)
    }

    suspend fun isRecipeFavorited(recipeId: Int): FavoriteRecipe? {
        return favoriteRecipeDao.getRecipeById(recipeId)
    }
}
