package com.dicoding.foodtopia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.foodtopia.R
import com.dicoding.foodtopia.databinding.ItemRecipeGridBinding
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import com.dicoding.foodtopia.ui.detail.RecipeDetailActivity

class RecipeAdapter(private var recipes: List<RandomRecipesResponse.RecipesItem>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    fun updateData(newRecipes: List<RandomRecipesResponse.RecipesItem>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipeViewHolder(private val binding: ItemRecipeGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RandomRecipesResponse.RecipesItem) {
            binding.apply {
                recipeName.text = recipe.name
                recipeCalories.text = "${recipe.calories ?: "0"} cal"
                recipeCategory.text = recipe.recipeCategory ?: "Recipe"

                // Load image with placeholder and error handling
                Glide.with(root.context)
                    .load(when (recipe.images) {
                        is List<*> -> (recipe.images as List<String>).firstOrNull()
                        is String -> recipe.images as String
                        else -> null
                    })
                    .placeholder(R.drawable.placeholder_recipe)
                    .error(R.drawable.placeholder_recipe)
                    .centerCrop()
                    .into(recipeImage)

                root.setOnClickListener {
                    val context = root.context
                    val intent = Intent(context, RecipeDetailActivity::class.java)
                    intent.putExtra("RECIPE_ITEM", recipe)
                    context.startActivity(intent)
                }
            }
        }
    }
}
