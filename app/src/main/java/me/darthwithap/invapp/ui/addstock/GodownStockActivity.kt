package me.darthwithap.invapp.ui.addstock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import me.darthwithap.invapp.R
import me.darthwithap.invapp.databinding.ActivityGodownStockBinding
import me.darthwithap.invapp.ui.viewmodel.StockViewModel

class GodownStockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGodownStockBinding
    private lateinit var stockViewModel: StockViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGodownStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_godown_stock) as NavHostFragment
        navController = navHostFragment.findNavController()

        if (intent != null) {
            if (intent.hasExtra(getString(R.string.intent_extra_godown_id))) {
                intent.getStringExtra(getString(R.string.intent_extra_godown_id))?.let {
                    stockViewModel.setCurrentGodownId(
                        it
                    )
                }
                intent.getStringExtra(getString(R.string.intent_extra_godown_name))?.let {
                    stockViewModel.setCurrentGodownName(
                        it
                    )
                }
            }
        }
    }
}