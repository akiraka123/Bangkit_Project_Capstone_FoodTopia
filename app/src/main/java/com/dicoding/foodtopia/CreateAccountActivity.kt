package com.dicoding.foodtopia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.foodtopia.data.AccountManager
import com.dicoding.foodtopia.data.model.Account
import com.dicoding.foodtopia.databinding.ActivityCreateAccountBinding
import com.dicoding.foodtopia.data.api.ApiClient
import com.dicoding.foodtopia.data.api.FoodTopiaApiService
import com.dicoding.foodtopia.data.api.RegisterRequest
import kotlinx.coroutines.launch

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        accountManager = AccountManager(this)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.createAccountButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (validateInput(name, email, password)) {
                lifecycleScope.launch {
                    try {
                        val response = ApiClient.apiService.register(
                            RegisterRequest(name, email, password)
                        )
                        
                        if (response.isSuccessful) {
                            Toast.makeText(this@CreateAccountActivity, "Account created successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@CreateAccountActivity, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@CreateAccountActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@CreateAccountActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.loginText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validateInput(name: String, email: String, password: String): Boolean {
        if (name.isEmpty()) {
            binding.nameEditText.error = "Name is required"
            return false
        }

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
} 