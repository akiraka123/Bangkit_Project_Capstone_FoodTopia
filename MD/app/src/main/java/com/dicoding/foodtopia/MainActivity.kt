package com.dicoding.foodtopia

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dicoding.foodtopia.ui.favorite.FavoriteFragment
import com.dicoding.foodtopia.ui.home.HomeFragment
import com.dicoding.foodtopia.ui.profile.ProfileFragment
import com.dicoding.foodtopia.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_search -> SearchFragment()
                R.id.nav_favorite -> FavoriteFragment()
                R.id.nav_account -> ProfileFragment()
                else -> HomeFragment() // Default ke HomeFragment
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, selectedFragment)
                .commit()
            true
        }

        // FAB klik
        fab.setOnClickListener {
            Toast.makeText(this, "FAB clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        super.onBackPressed()
    }
}
