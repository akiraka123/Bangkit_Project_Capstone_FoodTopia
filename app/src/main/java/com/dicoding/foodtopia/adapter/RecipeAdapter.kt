package com.dicoding.foodtopia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.foodtopia.databinding.ItemRecipeBinding
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import com.dicoding.foodtopia.ui.detail.RecipeDetailActivity

class RecipeAdapter(private var recipes: List<RandomRecipesResponse.RecipesItem>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    fun updateData(newRecipes: List<RandomRecipesResponse.RecipesItem>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RandomRecipesResponse.RecipesItem) {
            binding.recipeName.text = recipe.name
            binding.recipeCalories.text = recipe.calories

            if (recipe.images is List<*>) {
                Glide.with(binding.root.context)
                    .load((recipe.images as List<String>)[0])
                    .into(binding.recipeImage)
            } else if (recipe.images is String) {
                Glide.with(binding.root.context)
                    .load(recipe.images as String)
                    .into(binding.recipeImage)
            }

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, RecipeDetailActivity::class.java)
                intent.putExtra("RECIPE_ITEM", recipe)
                context.startActivity(intent)
            }
        }
    }
}
