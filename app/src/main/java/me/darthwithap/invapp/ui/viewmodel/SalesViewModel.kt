package me.darthwithap.invapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.darthwithap.invapp.data.domain.models.Sale
import kotlin.random.Random

class SalesViewModel : ViewModel() {

    private var _sales: MutableLiveData<List<Sale>> = MutableLiveData()
    val sales: LiveData<List<Sale>> = _sales
    private val saleList: MutableList<Sale> = mutableListOf()

    fun getSales() {
        val rand = Random.nextInt(1, 10)
        saleList.clear()
        for (i in 0..rand) {
            saleList.add(
                Sale(
                    "Customer $i",
                    "Seller $i",
                    "Sale Details $i",
                    isNewSale = Random.nextBoolean()
                )
            )
        }
        _sales.postValue(saleList)
    }
}