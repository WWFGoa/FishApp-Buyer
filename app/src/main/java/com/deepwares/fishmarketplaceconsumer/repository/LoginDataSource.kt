package com.deepwares.fishmarketplaceconsumer.repository

import com.deepwares.fishmarketplaceconsumer.model.LoggedInUser
import java.io.IOException
import java.util.*
import com.deepwares.fishmarketplaceconsumer.model.Result

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

