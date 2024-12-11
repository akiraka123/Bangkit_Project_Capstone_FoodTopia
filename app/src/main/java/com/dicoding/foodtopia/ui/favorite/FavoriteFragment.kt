package com.dicoding.foodtopia.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.foodtopia.R
import com.dicoding.foodtopia.adapter.RecipeAdapter
import com.dicoding.foodtopia.data.database.FavoriteRecipeDatabase
import com.dicoding.foodtopia.data.repository.FavoriteRecipeRepository
import com.dicoding.foodtopia.data.viewmodel.FavoriteRecipeViewModel
import com.dicoding.foodtopia.data.viewmodel.FavoriteRecipeViewModelFactory
import com.dicoding.foodtopia.ui.home.HomeFragment

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeTitle: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnBack: ImageButton
    private lateinit var viewModel: FavoriteRecipeViewModel
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recipeTitle = view.findViewById(R.id.recipeTitle)
        progressBar = view.findViewById(R.id.progressBar)
        btnBack = view.findViewById(R.id.btnBack)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recipeTitle.text = getString(R.string.favorite_recipes)

        adapter = RecipeAdapter(emptyList())
        recyclerView.adapter = adapter

        val database = FavoriteRecipeDatabase.getDatabase(requireContext())
        val favoriteRecipeRepository = FavoriteRecipeRepository(database.favoriteRecipeDao())
        val factory = FavoriteRecipeViewModelFactory(favoriteRecipeRepository)
        viewModel = ViewModelProvider(this, factory)[FavoriteRecipeViewModel::class.java]

        observeFavoriteRecipes()

        btnBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
        }

        return view
    }

    private fun observeFavoriteRecipes() {
        progressBar.visibility = View.VISIBLE

        viewModel.getFavoriteRecipes().observe(viewLifecycleOwner) { recipes ->
            adapter.updateData(recipes.map { it.toRecipeItem() })
            progressBar.visibility = View.GONE
        }
    }
}
