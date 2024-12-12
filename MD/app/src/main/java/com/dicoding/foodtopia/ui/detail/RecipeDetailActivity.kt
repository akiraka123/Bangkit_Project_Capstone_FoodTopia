package com.dicoding.foodtopia.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dicoding.foodtopia.data.database.FavoriteRecipe
import com.dicoding.foodtopia.data.database.FavoriteRecipeDatabase
import com.dicoding.foodtopia.databinding.ActivityRecipeDetailBinding
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import com.dicoding.foodtopia.data.repository.FavoriteRecipeRepository
import com.dicoding.foodtopia.data.viewmodel.FavoriteRecipeViewModel
import com.dicoding.foodtopia.data.viewmodel.FavoriteRecipeViewModelFactory
import com.dicoding.foodtopia.R
import com.dicoding.foodtopia.model.DietClassifier
import com.dicoding.foodtopia.data.TokenManager
import com.dicoding.foodtopia.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private lateinit var viewModel: FavoriteRecipeViewModel
    private lateinit var dietClassifier: DietClassifier
    private var isFavorite = false
    private var recipe: RandomRecipesResponse.RecipesItem? = null
    private lateinit var token: String
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dietClassifier = DietClassifier(this)

        recipe = intent.getParcelableExtra("RECIPE_ITEM")

        if (recipe != null) {
            binding.recipeName.text = recipe?.name ?: "No Name"
            binding.recipeCalories.text = "${recipe?.calories ?: "N/A"}"
            binding.recipeServings.text = "${recipe?.recipeServings ?: "N/A"}"
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

            // Classify diet type
            try {
                val dietClass = dietClassifier.classify(
                    calories = recipe?.calories?.toFloatOrNull() ?: 0f,
                    fatContent = recipe?.fatContent?.toFloatOrNull() ?: 0f,
                    saturatedFatContent = recipe?.saturatedFatContent?.toFloatOrNull() ?: 0f,
                    sodiumContent = recipe?.sodiumContent?.toFloatOrNull() ?: 0f,
                    carbohydrateContent = recipe?.carbohydrateContent?.toFloatOrNull() ?: 0f,
                    fiberContent = recipe?.fiberContent?.toFloatOrNull() ?: 0f,
                    sugarContent = recipe?.sugarContent?.toFloatOrNull() ?: 0f,
                    proteinContent = recipe?.proteinContent?.toFloatOrNull() ?: 0f
                )
                binding.recipeDietType.text = "Diet Category: ${dietClassifier.getDietLabel(dietClass)}"
            } catch (e: Exception) {
                binding.recipeDietType.text = "Diet Category: Unclassified"
                e.printStackTrace()
            }

            if (recipe?.images is List<*>) {
                Glide.with(this)
                    .load((recipe?.images as List<String>)[0])
                    .into(binding.recipeImage)
            } else if (recipe?.images is String) {
                Glide.with(this)
                    .load(recipe?.images as String)
                    .into(binding.recipeImage)
            }

            tokenManager = TokenManager(this)

            token = tokenManager.getToken() ?: ""

            if (token.isEmpty()) {
                startLoginActivity()
                return
            }

            val repository = FavoriteRecipeRepository()
            val factory = FavoriteRecipeViewModelFactory(repository)
            viewModel = ViewModelProvider(this, factory)[FavoriteRecipeViewModel::class.java]

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
        viewModel.getFavoriteRecipes(token)
        viewModel.favorites.observe(this) { favorites ->
            isFavorite = favorites.any { it.id == recipe?.id }
            updateFavoriteButton()
        }
    }

    private fun addRecipeToFavorites() {
        val recipeId = recipe?.id ?: return
        
        lifecycleScope.launch {
            if (!tokenManager.refreshTokenIfNeeded()) {
                startLoginActivity()
                return@launch
            }
            
            token = tokenManager.getToken() ?: ""
            viewModel.addFavorite(token, recipeId)
            Toast.makeText(this@RecipeDetailActivity, "Adding to favorites...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeRecipeFromFavorites() {
        val recipeId = recipe?.id ?: return
        
        lifecycleScope.launch {
            if (!tokenManager.refreshTokenIfNeeded()) {
                startLoginActivity()
                return@launch
            }
            
            token = tokenManager.getToken() ?: ""
            viewModel.removeFavorite(token, recipeId)
            Toast.makeText(this@RecipeDetailActivity, "Removing from favorites...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFavoriteButton() {
        binding.favoriteButton.setImageResource(
            if (isFavorite) R.drawable.favorite_filled
            else R.drawable.favorite_border
        )
    }

    private fun startLoginActivity() {
        Toast.makeText(this, "Please login to continue", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        dietClassifier.close()
    }
}
