package com.deepwares.fishmarketplaceconsumer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.amplifyframework.core.Amplify
import com.deepwares.fishmarketplaceconsumer.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    val TAG = SplashActivity::class.java.name
    val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logo.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        checkLogin()


    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }


    fun checkLogin() {
        Amplify.Auth.fetchAuthSession(
            { result ->
                Log.i("AmplifyQuickstart", result.toString())

                if (!result.isSignedIn) {
                    Log.i(TAG, "ApiQuickstart | User not signed in. Go to login")
                    showLogin()
                } else {
                    Log.i(TAG, "ApiQuickstart | User Logged in. Go to Main")
                    goToMain()
                }
            },
            { error -> Log.e("AmplifyQuickstart", error.toString()) }
        )
    }

    private fun goToMain() {

        Log.d(TAG, "User is already logged in. Going to MainNavActivity")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLogin() {
        Log.d(TAG, "User is already logged in. Going to MainNavActivity")
        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)


    }
}