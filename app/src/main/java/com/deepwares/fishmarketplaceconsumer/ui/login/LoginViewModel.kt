package com.deepwares.fishmarketplaceconsumer.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.SignInResult
import com.amazonaws.mobile.client.results.SignUpResult
import com.amazonaws.services.cognitoidentityprovider.model.InvalidPasswordException
import com.amazonaws.services.cognitoidentityprovider.model.UserNotFoundException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.auth.options.AuthSignUpOptions.*
import com.amplifyframework.core.Amplify
import com.deepwares.fishmarketplaceconsumer.App
import com.deepwares.fishmarketplaceconsumer.R

import com.deepwares.fishmarketplaceconsumer.model.LoginError
import com.deepwares.fishmarketplaceconsumer.repository.Preferences
import java.lang.Exception

class LoginViewModel() : ViewModel() {

    val TAG = LoginViewModel::class.java.simpleName
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm
    val loginForm = MutableLiveData<Boolean>(true)

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {

        Amplify.Auth.signIn(
            username,
            password,
            { result ->
                Log.i(
                    "AuthQuickstart",
                    if (result.isSignInComplete) "Sign in succeeded" else "Sign in not complete"
                )

                val currentUser = Amplify.Auth.currentUser

                if (result.isSignInComplete) {
                    Log.d(TAG, "LOGIN - onResult :  $result | Fetching user attributes")
                    Preferences.setUserId(App.INSTANCE, currentUser.userId)
                    Log.d(
                        TAG,
                        "user attributes | name :   ${currentUser.username}"
                    )
                    //result?.
                    _loginResult.postValue(LoginResult(success = LoggedInUserView(displayName = currentUser.username!!)))
                }

            },
            { e ->
                Log.e("AuthQuickstart", e.toString(), e)
                Log.e(TAG, "LOGIN - onError | Could not login successfully", e)
                var loginError = LoginError.Unknown
                if (e is UserNotFoundException || e.cause is UserNotFoundException) {
                    loginError = LoginError.UserNotFound
                    loginForm.postValue(false)
                } else if (e is InvalidPasswordException || e.cause is InvalidPasswordException) {
                    loginError = LoginError.PasswordMismatch
                    _loginForm.value =
                        LoginFormState(passwordError = R.string.invalid_password)
                }

                _loginResult.postValue(
                    LoginResult(
                        error = R.string.login_failed,
                        errorType = loginError
                    )
                )
            }
        )
    }


    fun loginDataChanged(
        phone: String,
        password: String,
        confirmPassword: String,
        name: String

    ) {

        if (loginForm.value!!) {
            if (phone.isNullOrEmpty() || phone.length != 10) {
                _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
            } else if (!isPasswordValid(password)) {
                _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
            } else {
                _loginForm.value = LoginFormState(isDataValid = true)
            }
        } else {
            if (phone.isNullOrEmpty() || phone.length != 10) {
                _loginForm.value =
                    LoginFormState(phoneError = R.string.invalid_phone)
            } else if (!isPasswordValid(password)) {
                _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
            } else if (!isPasswordValidSignup(password, confirmPassword)) {
                _loginForm.value =
                    LoginFormState(confirmPasswordError = R.string.confirm_mismatch_password)
            } else {
                _loginForm.value = LoginFormState(isDataValid = true)
            }
        }
    }


    fun register(phone: String, password: String, name: String) {
        val username = "+91$phone"


        Amplify.Auth.signUp(
            username,
            password,
            builder().userAttribute(AuthUserAttributeKey.name(), name)
                .userAttribute(AuthUserAttributeKey.phoneNumber(), username)
                //.userAttribute(AuthUserAttributeKey.custom("group"), "seller")
                .build(),
            { result ->
                Log.i("AuthQuickStart", "Result: $result")
                result.user?.let {
                    login(username, password)
                    Preferences.setUserId(App.INSTANCE, it.userId)
                }

            },
            { e ->
                Log.e(TAG, "REGISTER - onError | Could not login successfully", e)
                var loginError = LoginError.Unknown
                var errorToast = R.string.register_failed
                if (e is UserNotFoundException || e.cause is UserNotFoundException) {
                    loginError = LoginError.UserNotFound
                    errorToast = R.string.user_does_not_exist
                    loginForm.postValue(false)
                } else if (e is InvalidPasswordException || e.cause is InvalidPasswordException) {
                    loginError = LoginError.PasswordMismatch
                    _loginForm.value =
                        LoginFormState(passwordError = R.string.invalid_password)
                }

                _loginResult.postValue(
                    LoginResult(
                        error = errorToast,
                        errorType = loginError
                    )
                )
            }
        )
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 7
    }

    private fun isPasswordValidSignup(password: String, confirmPassword: String): Boolean {
        return isPasswordValid(password) && password.equals(confirmPassword)
    }
}
