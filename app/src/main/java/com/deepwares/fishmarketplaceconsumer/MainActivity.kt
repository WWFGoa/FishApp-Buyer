package com.deepwares.fishmarketplaceconsumer

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.BuyerPushToken
import com.deepwares.fishmarketplace.interfaces.SpeciesSelector
import com.deepwares.fishmarketplace.model.Species
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import java.util.*

class MainActivity : AppCompatActivity(), SpeciesSelector {

    val TAG = MainActivity::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val drawer: DrawerLayout = findViewById(R.id.nav_drawer)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration.Builder(
            setOf(
                R.id.navigation_search, R.id.navigation_listings, R.id.navigation_orders
            )
        ).setOpenableLayout(drawer)
            .setFallbackOnNavigateUpListener(object : AppBarConfiguration.OnNavigateUpListener {
                override fun onNavigateUp(): Boolean {
                    if (navController.currentDestination?.id == R.id.navigation_fish_info) {
                        navController.navigate(R.id.navigation_search)
                        return true
                    }
                    return false
                }
            }).build()
        // setupActionBarWithNavController(navController)
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
        updateToken()
    }

    fun updateToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d(TAG, "Got new token" + token)

            token?.let {
                Amplify.API.query(
                    ModelQuery.list(
                        BuyerPushToken::class.java,
                        BuyerPushToken.USER_ID.eq(Amplify.Auth.currentUser.userId)
                    ), {
                        Log.d(TAG, "Result for push token : $it")
                        if (it.hasData() && !it.hasErrors()) {
                            val items = it.data.items
                            var hasToken = false
                            var id = UUID.randomUUID().toString()

                            items?.forEach {
                                Log.d(TAG, "Token : " + it)
                                hasToken = true
                                id = it.id
                            }
                            val buyerToken = BuyerPushToken.Builder()
                                .userId(Amplify.Auth.currentUser.userId)
                                .token(token)
                                .id(id).build()
                            Amplify.API.mutate(
                                if (hasToken) ModelMutation.update(buyerToken) else ModelMutation.create(
                                    buyerToken
                                ), {
                                    Log.d(
                                        TAG,
                                        "Create or Update Token result | hasToken : $hasToken | result : " + it
                                    )
                                }, {
                                    Log.e(TAG, "Create or Update Token error : $it", it)
                                })


                        }
                    }, {
                        Log.e(TAG, "Error fetching push : $it", it)
                    })
            }
        })
    }

    override fun selectSpecies(species: Species, position: Int) {

    }
}