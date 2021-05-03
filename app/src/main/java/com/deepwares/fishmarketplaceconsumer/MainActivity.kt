package com.deepwares.fishmarketplaceconsumer

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amazonaws.mobile.client.AWSMobileClient
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.BuyerPushToken
import com.deepwares.fishmarketplace.interfaces.SpeciesSelector
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.repository.Preferences
import com.deepwares.fishmarketplaceconsumer.ui.tutorial.TutorialFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), SpeciesSelector {

    val TAG = MainActivity::class.java.name
    lateinit var profileName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val drawer: DrawerLayout = findViewById(R.id.nav_drawer)
        val navController = findNavController(R.id.nav_host_fragment)

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

        toolbar.setNavigationOnClickListener {
            navController.currentDestination?.let {
                when (it.id) {
                    R.id.navigation_search,
                    R.id.navigation_orders,
                    R.id.navigation_listings -> {
                        drawer.open()
                    }
                    else -> {
                        navController.navigateUp()
                    }
                }
            }
        }
        nav_view_drawer.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout -> logout()
                R.id.nav_share -> shareApp()
                R.id.menu_send -> feedback(resources.getString(R.string.menu_send))
                R.id.menu_tutorial -> {
                    val tutorialFragment =
                        TutorialFragment()
                    tutorialFragment.show(supportFragmentManager, "")
                }
            }
            return@setNavigationItemSelectedListener true
        }
        updateToken()
        fetchUserProfile()
        drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                drawerView.findViewById<TextView>(R.id.name)
                    ?.setText(Preferences.getName(this@MainActivity))
                drawerView.findViewById<TextView>(R.id.username)
                    ?.setText(Amplify.Auth.currentUser.username)

            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerStateChanged(newState: Int) {
            }
        })
        findViewById<TextView>(R.id.version).setText(BuildConfig.VERSION_NAME + "(" + BuildConfig.VERSION_CODE + ")")
    }

    private fun fetchUserProfile() {
        val existingName = Preferences.getName(this)
        if (existingName == null) {
            Amplify.Auth.fetchUserAttributes({ list ->
                val name = list.find { it.key == AuthUserAttributeKey.name() }
                Preferences.setName(this, name?.value)
            }, {})
        }
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


    private fun feedback(feedback: String?) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_EMAIL, "")
            // putExtra(Intent.EXTRA_STREAM, attachment)
            setData(
                Uri.parse(
                    "mailto:" + getString(R.string.email_feedback) +
                            "?subject=" + Uri.encode(if (feedback.isNullOrEmpty()) "Cetacean App Feedback" else feedback)
                )
            )

        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun shareApp() {
        val pm = packageManager
        try {

            val waIntent = Intent(Intent.ACTION_SEND)
            waIntent.type = "text/plain"
            val text =
                "https://drive.google.com/file/d/1yfu8iRb1owdd_XJ5NBmSqPiwgCAOnHmJ/view?usp=sharing"

            val info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA)
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp")

            waIntent.putExtra(Intent.EXTRA_TEXT, text)
            val handlerExists = waIntent.resolveActivity(pm) != null

            if (handlerExists) {
                startActivity(Intent.createChooser(waIntent, "Share with"))
            } else {
                Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show()
            }


        } catch (e: Exception) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun logout() {
        Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show()
        Preferences.logout(this)
        Amplify.Auth.signOut({
            Log.d(TAG, "Logout success")
        }, {
            Log.d(TAG, "Logout Error", it)
        })

        AWSMobileClient.getInstance().signOut()
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish()


    }
}