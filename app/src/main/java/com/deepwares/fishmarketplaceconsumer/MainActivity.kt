package com.deepwares.fishmarketplaceconsumer

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.deepwares.fishmarketplace.interfaces.SpeciesSelector
import com.deepwares.fishmarketplace.model.Species

class MainActivity : AppCompatActivity(), SpeciesSelector {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_search, R.id.navigation_listings, R.id.navigation_orders
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.navigation_search,
                R.id.navigation_orders,
                R.id.navigation_listings -> {
                    navView?.visibility = View.VISIBLE
                }
                else -> {
                    navView?.visibility = View.GONE
                }
            }
        }
    }

    override fun selectSpecies(species: Species, position: Int) {

    }
}