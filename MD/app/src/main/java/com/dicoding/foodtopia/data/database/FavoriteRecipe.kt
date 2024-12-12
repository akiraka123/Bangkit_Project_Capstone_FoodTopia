package com.dicoding.foodtopia.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse

@Entity(tableName = "favorite_recipe")
data class FavoriteRecipe(
    @PrimaryKey
    val id: String,
    val name: String,
    val image: String?,
    val calories: String?,
    val servings: String?,
    val instructions: List<String>?,
    val ingredients: Map<String, String>?,
    val sodiumContent: String?,
    val sugarContent: String?,
    val fatContent: String?,
    val proteinContent: String?,
    val fiberContent: String?
) {
    fun toRecipeItem() = RandomRecipesResponse.RecipesItem(
        id = id,
        name = name,
        calories = calories,
        recipeServings = servings,
        recipeInstructions = instructions,
        recipeIngredientParts = ingredients?.keys?.toList(),
        recipeIngredientQuantities = ingredients?.values?.toList(),
        sodiumContent = sodiumContent,
        sugarContent = sugarContent,
        fatContent = fatContent,
        proteinContent = proteinContent,
        fiberContent = fiberContent,
        images = listOf(image ?: "")
    )
}

