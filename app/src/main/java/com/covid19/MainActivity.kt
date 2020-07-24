package com.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar))



        val bottomNavigationMenu = findViewById<BottomNavigationView>(R.id.bottom_navigation_menu)
        val navController = findNavController(R.id.content_fragment)
       // val appBar = AppBarConfiguration(setOf(R.id.home_fragment,R.id.search_fragment))


      // setupActionBarWithNavController(navController,appBar)
        bottomNavigationMenu.setupWithNavController(navController)
    }
}