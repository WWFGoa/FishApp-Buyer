package com.deepwares.fishmarketplaceconsumer.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.deepwares.fishmarketplaceconsumer.MainActivity
import com.deepwares.fishmarketplaceconsumer.R

import com.deepwares.fishmarketplaceconsumer.model.LoginError
import com.deepwares.fishmarketplaceconsumer.ui.tutorial.TutorialFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val phone = findViewById<EditText>(R.id.phone)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            register.isEnabled = loginState.isDataValid

            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
            if (loginState.confirmPasswordError != null) {
                confirmPassword.error = getString(loginState.confirmPasswordError)
            }
            if (loginState.nameError != null) {
                name.error = getString(loginState.nameError)
            }
            if (loginState.phoneError != null) {
                phone.error = getString(loginState.phoneError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error, loginResult.errorType)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
                handleLoginSuccess()
            }

        })

        loginViewModel.loginForm.observe(this@LoginActivity, Observer {
            updateLoginForm(it)
        })

        confirmPassword.afterTextChanged {
            validate()
        }

        password.apply {
            afterTextChanged {
                validate()
            }

            setOnEditorActionListener { _, actionId, _ ->
                if (loginViewModel.loginForm.value!!)
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE ->
                            loginViewModel.login(
                                phone.text.toString(),
                                password.text.toString()
                            )
                    }
                false
            }
        }

        name.apply {
            afterTextChanged {
                validate()
            }
        }
        phone.apply {
            afterTextChanged {
                validate()
            }

            setOnEditorActionListener { _, actionId, _ ->
                if (!loginViewModel.loginForm.value!!)
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE ->
                            loginViewModel.register(
                                phone.text.toString(),
                                password.text.toString(),
                                name.text.toString()
                            )
                    }
                false
            }
        }

        login.setOnClickListener {
            if (loginViewModel.loginFormState.value != null && loginViewModel.loginFormState.value!!.isDataValid) {
                loading.visibility = View.VISIBLE
                loginViewModel.login("+91" + phone.text.toString(), password.text.toString())
            } else {
                Toast.makeText(this, R.string.login_form_error, Toast.LENGTH_LONG).show()
            }
        }
        register.setOnClickListener {
            if (loginViewModel.loginFormState.value != null && loginViewModel.loginFormState.value!!.isDataValid) {
                loading.visibility = View.VISIBLE
                loginViewModel.register(
                    phone.text.toString(),
                    password.text.toString(),
                    name.text.toString()
                )
            } else {
                Toast.makeText(this, R.string.register_form_error, Toast.LENGTH_LONG).show()
            }

        }
    }

    fun validate() {
        loginViewModel.loginDataChanged(
            phone.text.toString(),
            password.text.toString(),
            confirmPassword.text.toString(),
            name.text.toString()
        )
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        /*
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()

         */
    }

    private fun showLoginFailed(@StringRes errorString: Int, errorType: LoginError?) {


        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()

    }

    private fun updateLoginForm(loginForm: Boolean) {
        register.visibility = if (loginForm) View.GONE else View.VISIBLE
        login.visibility = if (loginForm) View.VISIBLE else View.GONE
        name.visibility = if (loginForm) View.GONE else View.VISIBLE
        confirmPassword.visibility = if (loginForm) View.GONE else View.VISIBLE
    }


    override fun onBackPressed() {
        if (!loginViewModel.loginForm.value!!) {
            loginViewModel.loginForm.value = true
            return
        }
        super.onBackPressed()
    }

    fun handleLoginSuccess() {


        val tutorialFragment =
            TutorialFragment()
        tutorialFragment.show(supportFragmentManager, "")
    }

    fun handleRegisterSuccess() {

    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
