package me.darthwithap.invapp.ui.login

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import es.dmoral.toasty.Toasty
import me.darthwithap.api.models.entities.User
import me.darthwithap.invapp.R
import me.darthwithap.invapp.databinding.ActivityLoginBinding
import me.darthwithap.invapp.ui.AuthViewModel

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.etUsername
        val password = binding.etPassword
        val shopCode = binding.etShopCode
        val login = binding.btnLogin
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, {
            // disable login button unless username / password / shop code are valid
            login.isEnabled = it.isDataValid

            if (it.usernameError != null) {
                username.error = getString(it.usernameError)
            }
            if (it.passwordError != null) {
                password.error = getString(it.passwordError)
            }
            if (it.shopError != null) {
                shopCode.error = getString(it.shopError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, {

            loading.visibility = View.GONE
            if (it.error != null) {
                Log.d(TAG, "onCreate: Error: ${it.error}")
                showLoginFailed(it.error)
            }
            if (it.success != null) {
                updateUiWithUser(it.success)
            }
            setResult(Activity.RESULT_OK)

            // TODO finish() call invoke
            //Complete and destroy login activity once successful
            //finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                shopCode.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString(),
                    shopCode.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString(),
                            shopCode.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(
                    username.text.toString(),
                    password.text.toString(),
                    shopCode.text.toString()
                )
            }
        }
    }

    // TODO Use Domain User model here instead of the Api Model Entity User
    private fun updateUiWithUser(user: User) {
        val welcome = getString(R.string.welcome)
        // TODO : initiate successful logged in experience i.e. open Home Activity
        Toast.makeText(
            applicationContext,
            "$welcome ${user.displayName}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(errorString: String) {
        Toasty.error(applicationContext, errorString)
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