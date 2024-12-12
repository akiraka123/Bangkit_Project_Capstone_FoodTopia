package com.dicoding.foodtopia.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.foodtopia.data.repository.FavoriteRecipeRepository

class FavoriteRecipeViewModelFactory(
    private val repository: FavoriteRecipeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteRecipeViewModel::class.java)) {
            return FavoriteRecipeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
