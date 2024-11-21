package com.dicoding.foodtopia.data

import android.content.Context
import android.content.SharedPreferences
import com.dicoding.foodtopia.data.model.Account
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AccountManager(context: Context) {
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("AccountPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveAccount(account: Account) {
        val accounts = getAccounts().toMutableList()
        if (!accounts.any { it.email == account.email }) {
            accounts.add(account)
            val accountsJson = gson.toJson(accounts)
            sharedPreferences.edit().putString("accounts", accountsJson).apply()
        }
    }

    fun getAccounts(): List<Account> {
        val accountsJson = sharedPreferences.getString("accounts", "[]")
        val type = object : TypeToken<List<Account>>() {}.type
        return gson.fromJson(accountsJson, type) ?: emptyList()
    }

    fun validateCredentials(email: String, password: String): Boolean {
        return getAccounts().any { it.email == email && it.password == password }
    }

    fun getAccountByEmail(email: String): Account? {
        return getAccounts().find { it.email == email }
    }
} 