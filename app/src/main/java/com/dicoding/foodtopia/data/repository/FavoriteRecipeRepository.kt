package com.dicoding.foodtopia.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.foodtopia.data.api.ApiClient
import com.dicoding.foodtopia.data.api.AddFavoriteRequest
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse

class FavoriteRecipeRepository {
    private val _favorites = MutableLiveData<List<RandomRecipesResponse.RecipesItem>>()
    val favorites: LiveData<List<RandomRecipesResponse.RecipesItem>> = _favorites

    suspend fun getFavoriteRecipes(token: String) {
        try {
            val response = ApiClient.apiService.getFavorites("Bearer $token")
            if (response.isSuccessful) {
                _favorites.postValue(response.body()?.favorites ?: emptyList())
            } else {
                Log.e("FavoriteRepo", "Error getting favorites: ${response.errorBody()?.string()}")
                _favorites.postValue(emptyList())
            }
        } catch (e: Exception) {
            Log.e("FavoriteRepo", "Exception getting favorites", e)
            _favorites.postValue(emptyList())
        }
    }

    suspend fun addToFavorites(token: String, recipeId: String): Boolean {
        return try {
            val response = ApiClient.apiService.addToFavorites(
                "Bearer $token",
                AddFavoriteRequest(recipeId)
            )
            if (!response.isSuccessful) {
                Log.e("FavoriteRepo", "Error adding favorite: ${response.errorBody()?.string()}")
            }
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("FavoriteRepo", "Exception adding favorite", e)
            false
        }
    }

    suspend fun removeFromFavorites(token: String, recipeId: String): Boolean {
        return try {
            val response = ApiClient.apiService.removeFavorite("Bearer $token", recipeId)
            if (!response.isSuccessful) {
                Log.e("FavoriteRepo", "Error removing favorite: ${response.errorBody()?.string()}")
            }
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("FavoriteRepo", "Exception removing favorite", e)
            false
        }
    }
}