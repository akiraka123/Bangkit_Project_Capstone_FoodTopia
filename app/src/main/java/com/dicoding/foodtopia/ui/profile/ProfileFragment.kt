package com.dicoding.foodtopia.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.foodtopia.SplashActivity
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
        val sharedPreferences = requireContext().getSharedPreferences("FoodTopiaPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "User") ?: "User"
        binding.userName.text = userName
    }

    private fun setupClickListeners() {
        binding.editProfileButton.setOnClickListener {
            // TODO: Navigate to edit profile screen
        }

        binding.logoutButton.setOnClickListener {
            // Clear login state and user data
            requireContext().getSharedPreferences("FoodTopiaPrefs", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("is_logged_in", false)
                .remove("email")
                .remove("password")
                .remove("user_name")
                .apply()

            // Navigate to splash screen
            startActivity(Intent(requireContext(), SplashActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 