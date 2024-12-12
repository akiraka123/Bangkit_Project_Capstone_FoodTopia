package com.dicoding.foodtopia.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import com.dicoding.foodtopia.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    val recipes: LiveData<List<RandomRecipesResponse.RecipesItem>> = recipeRepository.recipes

    fun getRandomRecipes(count: Int) {
        viewModelScope.launch {
            recipeRepository.fetchRandomRecipes(count)
        }
    }

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            recipeRepository.searchRecipes(query)
        }
    }
}
