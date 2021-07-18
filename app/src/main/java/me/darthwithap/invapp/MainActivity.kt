package me.darthwithap.invapp

import android.content.Context
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
import me.darthwithap.invapp.databinding.ActivityMainBinding
import me.darthwithap.invapp.ui.viewmodel.AuthViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel

    private lateinit var navView: BottomNavigationView

    // TODO move to BaseActivity or BaseApplication
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(
            resources.getString(R.string.app_shared_preferences),
            Context.MODE_PRIVATE
        )

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val navController = navHostFragment.findNavController()
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_add_stock, R.id.navigation_delivery, R.id.navigation_invoice,
                R.id.navigation_orders, R.id.navigation_sales
            )
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_godown_add_stock -> {
                    navView.visibility = View.GONE
                }
                R.id.navigation_godown_check_stock -> {
                    navView.visibility = View.GONE
                }
                R.id.navigation_godown_stock_history -> {
                    navView.visibility = View.GONE
                }
                else -> navView.visibility = View.VISIBLE
            }
        }

        // Cannot set action bar as it is a no action bar activity
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        sharedPreferences.getString(resources.getString(R.string.prefs_key_token), null)?.let {
            // TODO get User with token from cache.
            //authViewModel.getCurrentUser(it)
        }

        authViewModel.token.observe({ lifecycle }) {
            it?.let {
                sharedPreferences.edit {
                    putString(resources.getString(R.string.prefs_key_token), it)
                }
            } ?: run {
                sharedPreferences.edit {
                    remove(resources.getString(R.string.prefs_key_token))
                }
            }
            // TODO Update UI
            //navController.navigateUp()
        }
    }
}