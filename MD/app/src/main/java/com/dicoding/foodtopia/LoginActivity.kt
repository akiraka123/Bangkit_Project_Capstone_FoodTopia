package com.dicoding.foodtopia

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.foodtopia.data.api.ApiClient
import com.dicoding.foodtopia.data.api.FoodTopiaApiService
import com.dicoding.foodtopia.data.api.LoginRequest
import com.dicoding.foodtopia.data.api.UserResponse
import com.dicoding.foodtopia.data.api.AuthResponse
import com.dicoding.foodtopia.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("FoodTopiaPrefs", Context.MODE_PRIVATE)

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
                lifecycleScope.launch {
                    try {
                        val response = ApiClient.apiService.login(LoginRequest(email, password))
                        if (response.isSuccessful) {
                            response.body()?.let { authResponse ->
                                // Save token and login state
                                saveAuthToken(authResponse)

                                // Get user profile
                                val userResponse = ApiClient.apiService.getUserProfile("Bearer ${authResponse.token}")
                                if (userResponse.isSuccessful) {
                                    userResponse.body()?.let { user ->
                                        sharedPreferences.edit().putString("user_name", user.name).apply()
                                    }
                                }

                                Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()
                            }
                        } else {
                            Toast.makeText(this@LoginActivity, "Invalid email or password", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.createAccountText.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }

        binding.forgotPasswordText.setOnClickListener {
            // TODO: Implement forgot password functionality
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
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

    private fun saveAuthToken(response: AuthResponse) {
        sharedPreferences.edit().apply {
            putString("token", response.token)
            putString("email", binding.emailEditText.text.toString())
            putBoolean("is_logged_in", true)
            putLong("token_expiry", System.currentTimeMillis() + (response.expiresIn * 1000))
            apply()
        }
    }
} 