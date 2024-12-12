package com.dicoding.foodtopia.data.modelrecipe

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RandomRecipesResponse(
	@SerializedName("recipes")
	val recipes: List<RecipesItem>? = null,
) : Parcelable {
	@Parcelize
	data class RecipesItem(
		@SerializedName("RecipeInstructions")
		@JsonAdapter(RecipeInstructionsDeserializer::class)
		val recipeInstructions: List<String>? = null,

		@SerializedName("Keywords")
		@JsonAdapter(KeywordsDeserializer::class)
		val keywords: List<String>? = null,

		@SerializedName("Images")
		@JsonAdapter(ImagesDeserializer::class)
		val images: @RawValue Any?,

		@SerializedName("RecipeId")
		val recipeId: String? = null,

		@SerializedName("RecipeIngredientParts")
		@JsonAdapter(RecipeIngredientPartsDeserializer::class)
		val recipeIngredientParts: List<String>? = null,

		@SerializedName("SodiumContent")
		val sodiumContent: String? = null,

		@SerializedName("SugarContent")
		val sugarContent: String? = null,

		@SerializedName("Calories")
		val calories: String? = null,

		@SerializedName("CholesterolContent")
		val cholesterolContent: String? = null,

		@SerializedName("Name")
		val name: String? = null,

		@SerializedName("Diet")
		val diet: String? = null,

		@SerializedName("RecipeCategory")
		val recipeCategory: String? = null,

		@SerializedName("RecipeIngredientQuantities")
		val recipeIngredientQuantities: List<String>? = null,

		@SerializedName("RecipeServings")
		val recipeServings: String? = null,

		@SerializedName("SaturatedFatContent")
		val saturatedFatContent: String? = null,

		@SerializedName("NameLower")
		val nameLower: String? = null,

		@SerializedName("CarbohydrateContent")
		val carbohydrateContent: String? = null,

		@SerializedName("id")
		val id: String? = null,

		@SerializedName("ID")
		val iD: Int? = null,

		@SerializedName("FatContent")
		val fatContent: String? = null,

		@SerializedName("ProteinContent")
		val proteinContent: String? = null,

		@SerializedName("FiberContent")
		val fiberContent: String? = null
	) : Parcelable
}
