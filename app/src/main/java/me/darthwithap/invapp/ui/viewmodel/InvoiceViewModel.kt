package me.darthwithap.invapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.darthwithap.api.models.entities.data.ProductData
import me.darthwithap.api.models.requests.CreateInvoiceRequest
import me.darthwithap.api.models.requests.PendingOrdersUpdateRequest
import me.darthwithap.invapp.data.invoice.InvoiceRepository
import me.darthwithap.invapp.ui.invoice.CreateInvoiceResult
import me.darthwithap.invapp.ui.orders.result.PendingOrdersResult
import me.darthwithap.invapp.ui.orders.result.PendingOrdersUpdateStatusResult
import me.darthwithap.invapp.utils.Result

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
                    _createInvoiceResult.postValue(CreateInvoiceResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun updatePendingOrdersStatusFor(invoiceId: String, productIds: List<String>) {
        viewModelScope.launch {
            when (val result = InvoiceRepository.updatePendingOrdersStatusFor(
                PendingOrdersUpdateRequest(
                    invoiceId, productIds
                )
            )) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _pendingOrdersUpdateStatusResult.postValue(
                        PendingOrdersUpdateStatusResult(
                            result.data
                        )
                    )
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _pendingOrdersUpdateStatusResult.postValue(
                        PendingOrdersUpdateStatusResult(
                            error = result.exception.message
                        )
                    )
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }
}