package com.dicoding.foodtopia.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TokenManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("FoodTopiaPrefs", Context.MODE_PRIVATE)
    
    fun getToken(): String? {
        return prefs.getString("token", null)
    }

    suspend fun refreshTokenIfNeeded(): Boolean {
        return withContext(Dispatchers.IO) {
            getToken() != null
        }
    }

    fun clearToken() {
        prefs.edit().apply {
            remove("token")
            remove("token_expiry")
            apply()
        }
    }
} 