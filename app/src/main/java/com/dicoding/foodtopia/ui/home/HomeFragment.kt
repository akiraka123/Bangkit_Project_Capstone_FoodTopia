package com.dicoding.foodtopia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.foodtopia.R
import com.dicoding.foodtopia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupGreeting()
        setupRecyclerView()
    }

    private fun setupGreeting() {
        // TODO: Get user's name from preferences/database
        binding.greetingText.text = getString(R.string.greeting_text, "John")
    }

    private fun setupRecyclerView() {
        binding.recipesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            // TODO: Set adapter with recipe data
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 