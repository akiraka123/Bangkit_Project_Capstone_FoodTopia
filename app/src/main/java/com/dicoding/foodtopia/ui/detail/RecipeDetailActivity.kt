package com.dicoding.foodtopia.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.foodtopia.data.database.FavoriteRecipe
import com.dicoding.foodtopia.data.database.FavoriteRecipeDatabase
import com.dicoding.foodtopia.databinding.ActivityRecipeDetailBinding
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import com.dicoding.foodtopia.data.repository.FavoriteRecipeRepository
import com.dicoding.foodtopia.data.viewmodel.FavoriteRecipeViewModel
import com.dicoding.foodtopia.data.viewmodel.FavoriteRecipeViewModelFactory
import com.dicoding.foodtopia.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private lateinit var viewModel: FavoriteRecipeViewModel
    private var isFavorite = false
    private var recipe: RandomRecipesResponse.RecipesItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipe = intent.getParcelableExtra("RECIPE_ITEM")

        if (recipe != null) {
            binding.recipeName.text = recipe?.name ?: "No Name"
            binding.recipeCalories.text = "Calories: ${recipe?.calories ?: "N/A"}"
            binding.recipeServings.text = "Servings: ${recipe?.recipeServings ?: "N/A"}"
            binding.recipeInstructions.text = recipe?.recipeInstructions?.joinToString("\n") ?: "No Instructions"

            if (recipe?.recipeIngredientParts != null && recipe?.recipeIngredientQuantities != null) {
                val ingredientText = recipe?.recipeIngredientParts?.zip(recipe?.recipeIngredientQuantities!!)
                    ?.joinToString("\n") { "${it.first}: ${it.second}" }
                binding.recipeIngredients.text = ingredientText
            } else {
                binding.recipeIngredients.text = "No Ingredients"
            }

            binding.recipeSodium.text = "Sodium: ${recipe?.sodiumContent ?: "N/A"}"
            binding.recipeSugar.text = "Sugar: ${recipe?.sugarContent ?: "N/A"}"
            binding.recipeFat.text = "Fat: ${recipe?.fatContent ?: "N/A"}"
            binding.recipeProtein.text = "Protein: ${recipe?.proteinContent ?: "N/A"}"
            binding.recipeFiber.text = "Fiber: ${recipe?.fiberContent ?: "N/A"}"

            if (recipe?.images is List<*>) {
                Glide.with(this)
                    .load((recipe?.images as List<String>)[0])
                    .into(binding.recipeImage)
            } else if (recipe?.images is String) {
                Glide.with(this)
                    .load(recipe?.images as String)
                    .into(binding.recipeImage)
            }

            val database = FavoriteRecipeDatabase.getDatabase(this)
            val repository = FavoriteRecipeRepository(database.favoriteRecipeDao())
            val factory = FavoriteRecipeViewModelFactory(repository)
            viewModel = ViewModelProvider(this, factory).get(FavoriteRecipeViewModel::class.java)

            checkIfFavorite()

            binding.favoriteButton.setOnClickListener {
                if (isFavorite) {
                    removeRecipeFromFavorites()
                } else {
                    addRecipeToFavorites()
                }
            }

            binding.CloseButton.setOnClickListener {
                finish() // Menutup activity dan kembali ke activity sebelumnya
            }
        }
    }

    private fun checkIfFavorite() {
        val recipeId = recipe?.id?.toIntOrNull() ?: return

        CoroutineScope(Dispatchers.Main).launch {
            val favorite = viewModel.isRecipeFavorited(recipeId)
            isFavorite = favorite
            updateFavoriteButton()
        }
    }

    private fun addRecipeToFavorites() {
        val recipeId = recipe?.id ?: return
        val imageUrl = when (val images = recipe?.images) {
            is List<*> -> {
                if (images.isNotEmpty() && images[0] is String) {
                    images[0] as String
                } else {
                    ""
                }
            }
            is String -> images
            else -> ""
        }

        val newFavorite = FavoriteRecipe(
            id = recipeId,
            name = recipe?.name ?: "",
            image = imageUrl,
            calories = recipe?.calories ?: "",
            servings = recipe?.recipeServings ?: "",
            instructions = recipe?.recipeInstructions ?: listOf(),
            ingredients = (recipe?.recipeIngredientParts ?: emptyList())
                .zip(recipe?.recipeIngredientQuantities ?: emptyList())
                .toMap(),
            sodiumContent = recipe?.sodiumContent,
            sugarContent = recipe?.sugarContent,
            fatContent = recipe?.fatContent,
            proteinContent = recipe?.proteinContent,
            fiberContent = recipe?.fiberContent
        )

        viewModel.addFavorite(newFavorite)
        isFavorite = true
        updateFavoriteButton()
        Toast.makeText(this, "Recipe added to favorites", Toast.LENGTH_SHORT).show()
    }

    private fun removeRecipeFromFavorites() {
        val recipeId = recipe?.id ?: return
        viewModel.removeFavorite(recipeId)
        isFavorite = false
        updateFavoriteButton()
        Toast.makeText(this, "Recipe removed from favorites", Toast.LENGTH_SHORT).show()
    }

    private fun updateFavoriteButton() {
        if (isFavorite) {
            binding.favoriteButton.setImageResource(R.drawable.favorite_filled)
        } else {
            binding.favoriteButton.setImageResource(R.drawable.favorite_border)
        }
    }
}
