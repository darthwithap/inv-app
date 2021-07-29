package me.darthwithap.invapp.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.edit
import es.dmoral.toasty.Toasty
import me.darthwithap.invapp.MainActivity
import me.darthwithap.invapp.R
import me.darthwithap.invapp.data.domain.models.User
import me.darthwithap.invapp.databinding.ActivityLoginBinding
import me.darthwithap.invapp.ui.viewmodel.AuthViewModel
import me.darthwithap.invapp.utils.extensions.afterTextChanged
import java.util.*

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding
    private var isDataValid = false

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(
            resources.getString(R.string.app_shared_preferences),
            Context.MODE_PRIVATE
        )

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.etUsername
        val password = binding.etPassword
        val shopCode = binding.etShopCode
        val login = binding.btnLogin
        val loading = binding.loading

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        authViewModel.loginFormState.observe(this@LoginActivity, {
            // disable login button unless username / password / shop code are valid
            login.isEnabled = it.isDataValid
            isDataValid = it.isDataValid

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

        authViewModel.loginResult.observe(this@LoginActivity, {

            loading.visibility = View.GONE
            if (it.error != null) {
                Log.d(TAG, "onCreate: Error: ${it.error}")
                showLoginFailed(it.error)
            }
            if (it.success != null) {
                updateUiWithUser(it.success)
            }
            setResult(Activity.RESULT_OK)
        })

        username.afterTextChanged {
            authViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                shopCode.text.toString()
            )
        }

        password.afterTextChanged {
            authViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                shopCode.text.toString()
            )
        }

        shopCode.apply {
            afterTextChanged {
                authViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString(),
                    shopCode.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        if (isDataValid) {
                            loading.visibility = View.VISIBLE
                            authViewModel.login(
                                username.text.toString(),
                                password.text.toString(),
                                shopCode.text.toString()
                            )
                        } else showLoginFailed("Fields cant be empty")
                    }
                }
                false
            }
        }

        login.setOnClickListener {
            if (isDataValid) {
                loading.visibility = View.VISIBLE
                authViewModel.login(
                    username.text.toString(),
                    password.text.toString(),
                    shopCode.text.toString()
                )
            } else showLoginFailed("Fields cant be empty")
        }
    }

    private fun updateUiWithUser(user: User?) {
        sharedPreferences.edit {
            putString(resources.getString(R.string.prefs_key_token), user?.token)
        }
        val welcome = getString(R.string.welcome)
        Toasty.success(
            applicationContext,
            "$welcome ${user?.displayName}",
        ).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showLoginFailed(errorString: String) {
        Toasty.error(applicationContext, errorString).show()
    }
}
