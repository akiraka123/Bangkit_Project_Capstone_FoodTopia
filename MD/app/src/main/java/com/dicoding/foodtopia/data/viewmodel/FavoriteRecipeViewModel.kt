package com.dicoding.foodtopia.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import com.dicoding.foodtopia.data.repository.FavoriteRecipeRepository
import kotlinx.coroutines.launch

class FavoriteRecipeViewModel(private val repository: FavoriteRecipeRepository) : ViewModel() {
    val favorites: LiveData<List<RandomRecipesResponse.RecipesItem>> = repository.favorites

    fun getFavoriteRecipes(token: String) {
        viewModelScope.launch {
            repository.getFavoriteRecipes(token)
        }
    }

    fun addFavorite(token: String, recipeId: String) {
        viewModelScope.launch {
            val success = repository.addToFavorites(token, recipeId)
            if (success) {
                // Refresh favorites list after successful addition
                getFavoriteRecipes(token)
            }
        }
    }

    fun removeFavorite(token: String, recipeId: String) {
        viewModelScope.launch {
            val success = repository.removeFromFavorites(token, recipeId)
            if (success) {
                // Refresh favorites list after successful removal
                getFavoriteRecipes(token)
            }
        }
    }
}
