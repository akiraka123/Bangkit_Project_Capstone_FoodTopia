package com.dicoding.foodtopia.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.foodtopia.R
import com.dicoding.foodtopia.adapter.RecipeAdapter
import com.dicoding.foodtopia.data.repository.RecipeRepository
import com.dicoding.foodtopia.data.viewmodel.RecipeViewModel
import com.dicoding.foodtopia.data.viewmodel.RecipeViewModelFactory
import com.dicoding.foodtopia.databinding.FragmentSearchBinding
import com.dicoding.foodtopia.ui.home.HomeFragment

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val recipeRepository = RecipeRepository()
    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory(recipeRepository)
    }

    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
        setupBackButton()

        recipeViewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
            binding.progressBar.visibility = View.GONE

            if (recipes.isNotEmpty()) {
                adapter.updateData(recipes)
            } else {
                Toast.makeText(context, "No recipes found", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = RecipeAdapter(emptyList())
        binding.recipesGrid.layoutManager = GridLayoutManager(context, 2)
        binding.recipesGrid.adapter = adapter
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotBlank()) {
                    searchRecipes(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun setupBackButton() {
        binding.btnBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
        }
    }

    private fun searchRecipes(query: String) {
        binding.progressBar.visibility = View.VISIBLE
        recipeViewModel.searchRecipes(query)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
