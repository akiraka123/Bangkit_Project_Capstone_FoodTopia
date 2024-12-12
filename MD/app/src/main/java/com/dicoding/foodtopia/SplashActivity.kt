package com.dicoding.foodtopia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Force Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        
        // Check if user is already logged in
        val sharedPreferences = getSharedPreferences("FoodTopiaPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)
        
        if (isLoggedIn) {
            // If logged in, go directly to MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
        
        // If not logged in, show splash screen
        setContentView(R.layout.activity_splash)

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<TextView>(R.id.createAccountButton).setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }
} 