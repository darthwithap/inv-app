package me.darthwithap.invapp.ui.invoice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import me.darthwithap.invapp.data.models.InvoiceEntryItem

private const val TAG = "InvoiceViewModel"

class InvoiceViewModel : ViewModel() {
    private var _invoiceProducts: MutableLiveData<List<InvoiceEntryItem>> = MutableLiveData()
    val invoiceProducts: LiveData<List<InvoiceEntryItem>> = _invoiceProducts

    private val productList: MutableList<InvoiceEntryItem> = mutableListOf()

    fun addItemToProductList() {
        productList.add(InvoiceEntryItem())
        Log.d(TAG, "addItemToProductList: ${productList.size}")
        _invoiceProducts.postValue(productList)
    }
}