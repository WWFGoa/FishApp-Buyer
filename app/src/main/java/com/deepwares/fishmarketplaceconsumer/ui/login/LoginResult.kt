package com.deepwares.fishmarketplaceconsumer.ui.login

import com.deepwares.fishmarketplaceconsumer.model.LoginError
import com.deepwares.fishmarketplaceconsumer.ui.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null,
    val errorType: LoginError? = null
)
