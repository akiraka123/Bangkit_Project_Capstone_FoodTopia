package com.dicoding.foodtopia

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.foodtopia.data.AccountManager
import com.dicoding.foodtopia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("FoodTopiaPrefs", Context.MODE_PRIVATE)
        accountManager = AccountManager(this)

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
                if (accountManager.validateCredentials(email, password)) {
                    // Save credentials and login state
                    sharedPreferences.edit().apply {
                        putString("email", email)
                        putString("password", password)
                        putBoolean("is_logged_in", true)
                        apply()
                    }

                    // Get account details
                    val account = accountManager.getAccountByEmail(email)
                    account?.let {
                        sharedPreferences.edit().putString("user_name", it.name).apply()
                    }

                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
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
} 