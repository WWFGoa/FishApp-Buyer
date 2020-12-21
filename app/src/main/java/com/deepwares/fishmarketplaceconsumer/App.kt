package com.deepwares.fishmarketplaceconsumer

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify

class App : Application() {
    val TAG = App::class.java.name

    companion object {
        lateinit var INSTANCE: App
        fun getInstance(): App {
            return INSTANCE
        }
    }

    override fun onCreate() {

        INSTANCE = this
        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(this)
            // Amplify.Auth.initialize(this)
            //  Amplify.API.initialize(this)
            Log.d(TAG, "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e(TAG, "Could not initialize Amplify", error)
        }
        super.onCreate()
    }

}