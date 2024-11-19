package com.dicoding.foodtopia.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.foodtopia.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

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
        
        setupSearchView()
        setupRecyclerView()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // TODO: Handle search query
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO: Handle search query change
                return true
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recipesGrid.apply {
            layoutManager = GridLayoutManager(context, 2)
            // TODO: Set adapter with recipe data
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 