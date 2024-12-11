package com.dicoding.foodtopia.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.foodtopia.data.database.FavoriteRecipe
import com.dicoding.foodtopia.data.repository.FavoriteRecipeRepository
import kotlinx.coroutines.launch

class FavoriteRecipeViewModel(private val repository: FavoriteRecipeRepository) : ViewModel() {

    fun getFavoriteRecipes(): LiveData<List<FavoriteRecipe>> {
        return repository.getFavoriteRecipes()
    }

    fun addFavorite(recipe: FavoriteRecipe) {
        viewModelScope.launch {
            repository.addToFavorites(recipe)
        }
    }

    fun removeFavorite(recipeId: String) {
        viewModelScope.launch {
            repository.deleteRecipeById(recipeId)
        }
    }

    suspend fun isRecipeFavorited(recipeId: Int): Boolean {
        return repository.isRecipeFavorited(recipeId) != null
    }
}
