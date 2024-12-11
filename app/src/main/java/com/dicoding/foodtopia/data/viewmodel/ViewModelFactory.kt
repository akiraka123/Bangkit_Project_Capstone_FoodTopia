package com.dicoding.foodtopia.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.foodtopia.data.repository.RecipeRepository

class RecipeViewModelFactory(private val recipeRepository: RecipeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeViewModel(recipeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
