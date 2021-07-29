package me.darthwithap.invapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import me.darthwithap.api.InvApiClient
import me.darthwithap.invapp.databinding.ActivityMainBinding
import me.darthwithap.invapp.databinding.ActivitySplashBinding
import me.darthwithap.invapp.ui.login.LoginActivity
import me.darthwithap.invapp.ui.viewmodel.AuthViewModel

private const val TAG = "Splash"

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var authViewModel: AuthViewModel

    // TODO move to BaseActivity or BaseApplication
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(
            resources.getString(R.string.app_shared_preferences),
            Context.MODE_PRIVATE
        )

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = sharedPreferences.getString(resources.getString(R.string.prefs_key_token), null)

        if (token != null) {
            InvApiClient.setAuthToken(token)
            Log.d(TAG, "onCreate: setting token in splash (not null): $token")
            authViewModel.setToken(token)
        } else {
            Log.d(TAG, "onCreate: setting token in splash (not null): $token")
            authViewModel.setToken(null)

        }

        authViewModel.token.observe({ lifecycle }) {
            it?.let { token ->
                sharedPreferences.edit {
                    putString(resources.getString(R.string.prefs_key_token), token)
                }
                Log.d(TAG, "onPostCreate: not null token observing: $token")
                startActivity(Intent(this, MainActivity::class.java))
            } ?: run {
                sharedPreferences.edit {
                    remove(resources.getString(R.string.prefs_key_token))
                }
                Log.d(TAG, "onPostCreate: null token observing: moving to login")
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }

    }

}