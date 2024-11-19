package com.dicoding.foodtopia

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.foodtopia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("FoodTopiaPrefs", Context.MODE_PRIVATE)

        // Load saved credentials if they exist
        loadSavedCredentials()

        setupClickListeners()
    }

    private fun loadSavedCredentials() {
        binding.emailEditText.setText(sharedPreferences.getString("email", ""))
        binding.passwordEditText.setText(sharedPreferences.getString("password", ""))
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (validateInput(email, password)) {
                // Save credentials
                saveCredentials(email, password)
                
                // Navigate to MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish() // Close LoginActivity
            }
        }

        binding.createAccountText.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }

        binding.forgotPasswordText.setOnClickListener {
            // TODO: Implement forgot password functionality
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
        }

        binding.googleSignInButton.setOnClickListener {
            // TODO: Implement Google Sign In
            Toast.makeText(this, "Google Sign In clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.emailEditText.error = "Email is required"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEditText.error = "Invalid email format"
            return false
        }

        if (password.isEmpty()) {
            binding.passwordEditText.error = "Password is required"
            return false
        }

        if (password.length < 6) {
            binding.passwordEditText.error = "Password must be at least 6 characters"
            return false
        }

        return true
    }

    private fun saveCredentials(email: String, password: String) {
        sharedPreferences.edit().apply {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }
} 