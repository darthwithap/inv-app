package me.darthwithap.invapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.darthwithap.api.models.entities.data.ProductData
import me.darthwithap.api.models.requests.CreateInvoiceRequest
import me.darthwithap.api.models.requests.PendingOrdersUpdateRequest
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.data.godown.GodownRepository
import me.darthwithap.invapp.data.invoice.InvoiceRepository
import me.darthwithap.invapp.ui.addstock.GodownsResult
import me.darthwithap.invapp.ui.orders.CreateInvoiceResult
import me.darthwithap.invapp.ui.orders.PendingOrdersResult
import me.darthwithap.invapp.ui.orders.PendingOrdersUpdateStatusResult
import me.darthwithap.invapp.utils.Result

private const val TAG = "InvoiceViewModel"

class InvoiceViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _pendingOrdersResult = MutableLiveData<PendingOrdersResult>()
    val pendingOrdersResult: LiveData<PendingOrdersResult> = _pendingOrdersResult

    private var _createInvoiceResult = MutableLiveData<CreateInvoiceResult>()
    val createInvoiceResult: LiveData<CreateInvoiceResult> = _createInvoiceResult

    private var _pendingOrdersUpdateStatusResult =
        MutableLiveData<PendingOrdersUpdateStatusResult>()
    val pendingOrdersUpdateStatusResult: LiveData<PendingOrdersUpdateStatusResult> =
        _pendingOrdersUpdateStatusResult


    fun getPendingOrdersForGodown(godownId: String) {
        viewModelScope.launch {
            when (val result = InvoiceRepository.getPendingOrdersForGodown(godownId, true)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _pendingOrdersResult.postValue(PendingOrdersResult(result.data))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _pendingOrdersResult.postValue(PendingOrdersResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun createInvoice(name: String, products: List<ProductData>) {
        viewModelScope.launch {
            when (val result = InvoiceRepository.createInvoice(
                CreateInvoiceRequest(
                    name, products
                )
            )) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _createInvoiceResult.postValue(CreateInvoiceResult())
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _pendingOrdersResult.postValue(PendingOrdersResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun updatePendingOrdersStatusFor(
        pendingOrdersUpdateRequest: PendingOrdersUpdateRequest
    ) {
        viewModelScope.launch {
            when (val result =
                InvoiceRepository.updatePendingOrdersStatusFor(pendingOrdersUpdateRequest)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _pendingOrdersUpdateStatusResult.postValue(
                        PendingOrdersUpdateStatusResult(
                            success = result.data
                        )
                    )
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _pendingOrdersResult.postValue(PendingOrdersResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }
}