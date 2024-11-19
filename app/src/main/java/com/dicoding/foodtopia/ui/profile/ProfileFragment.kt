package com.dicoding.foodtopia.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.foodtopia.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupProfile()
        setupClickListeners()
    }

    private fun setupProfile() {
        // TODO: Load user profile data
        binding.userName.text = "John Doe" // Placeholder
    }

    private fun setupClickListeners() {
        binding.editProfileButton.setOnClickListener {
            // TODO: Navigate to edit profile screen
        }

        binding.logoutButton.setOnClickListener {
            // TODO: Handle logout
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 