package com.dicoding.foodtopia.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import com.dicoding.foodtopia.data.api.ApiClient
import retrofit2.Response

class RecipeRepository {
    private val _recipes = MutableLiveData<List<RandomRecipesResponse.RecipesItem>>()
    val recipes: LiveData<List<RandomRecipesResponse.RecipesItem>> get() = _recipes

    suspend fun fetchRandomRecipes(count: Int) {
        val response: Response<RandomRecipesResponse> = ApiClient.apiService.getRandomRecipes(count)

        if (response.isSuccessful) {
            val recipesList = response.body()?.recipes ?: emptyList()
            _recipes.postValue(recipesList)
        } else {
            _recipes.postValue(emptyList())
        }
    }

    suspend fun searchRecipes(query: String, page: Int = 1, limit: Int = 10) {
        val response: Response<RandomRecipesResponse> = ApiClient.apiService.searchRecipes(query, page, limit)
        if (response.isSuccessful) {
            _recipes.postValue(response.body()?.recipes ?: emptyList())
        } else {
            _recipes.postValue(emptyList())
        }
    }
}
