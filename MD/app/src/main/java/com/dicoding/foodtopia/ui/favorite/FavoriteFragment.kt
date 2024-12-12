package com.dicoding.foodtopia.ui.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.foodtopia.R
import com.dicoding.foodtopia.adapter.RecipeAdapter
import com.dicoding.foodtopia.data.repository.FavoriteRecipeRepository
import com.dicoding.foodtopia.data.viewmodel.FavoriteRecipeViewModel
import com.dicoding.foodtopia.data.viewmodel.FavoriteRecipeViewModelFactory
import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import com.dicoding.foodtopia.ui.home.HomeFragment
import com.dicoding.foodtopia.data.TokenManager
import com.dicoding.foodtopia.LoginActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeTitle: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnBack: ImageButton
    private lateinit var viewModel: FavoriteRecipeViewModel
    private lateinit var adapter: RecipeAdapter
    private lateinit var token: String
    private lateinit var tokenManager: TokenManager

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

        tokenManager = TokenManager(requireContext())
        
        btnBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
        }

        setupViewModel()

        return view
    }

    private fun setupViewModel() {
        val repository = FavoriteRecipeRepository()
        val factory = FavoriteRecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[FavoriteRecipeViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFavorites()
    }

    private fun loadFavorites() {
        viewLifecycleOwner.lifecycleScope.launch {
            token = tokenManager.getToken() ?: ""
            if (token.isEmpty()) {
                startLoginActivity()
                return@launch
            }
            
            observeFavoriteRecipes()
        }
    }

    private fun observeFavoriteRecipes() {
        progressBar.visibility = View.VISIBLE
        
        viewModel.getFavoriteRecipes(token)
        
        viewModel.favorites.observe(viewLifecycleOwner) { recipes: List<RandomRecipesResponse.RecipesItem> ->
            adapter.updateData(recipes)
            progressBar.visibility = View.GONE
        }
    }

    private fun startLoginActivity() {
        Toast.makeText(requireContext(), "Please login to view favorites", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
