package com.deepwares.fishmarketplaceconsumer

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify

class App : Application() {
    val TAG = App::class.java.name
    override fun onCreate() {
        super.onCreate()
        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.configure(this)
            Log.i(TAG, "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e(TAG, "Could not initialize Amplify", error)
        }
    }

}