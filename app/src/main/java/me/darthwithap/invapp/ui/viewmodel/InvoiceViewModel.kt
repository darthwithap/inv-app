package me.darthwithap.invapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.darthwithap.api.models.entities.data.ProductData
import me.darthwithap.api.models.requests.CreateInvoiceRequest
import me.darthwithap.api.models.requests.PendingOrdersUpdateRequest
import me.darthwithap.invapp.R
import me.darthwithap.invapp.data.repository.InvoiceRepository
import me.darthwithap.invapp.ui.invoice.InvoiceFormState
import me.darthwithap.invapp.ui.invoice.result.CreateInvoiceResult
import me.darthwithap.invapp.ui.orders.result.PendingOrdersResult
import me.darthwithap.invapp.ui.orders.result.PendingOrdersUpdateStatusResult
import me.darthwithap.invapp.utils.Result

class InvoiceViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _pendingOrdersResult = MutableLiveData<PendingOrdersResult>()
    val pendingOrdersResult: LiveData<PendingOrdersResult> = _pendingOrdersResult

    private val _invoiceForm = MutableLiveData<InvoiceFormState>()
    val invoiceFormState: LiveData<InvoiceFormState> = _invoiceForm

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
                    val invoices = result.data.filter { invoice ->
                        invoice.products?.filter { product ->
                            product.godown == godownId
                        }?.isNotEmpty()!!
                    }
                    _pendingOrdersResult.postValue(PendingOrdersResult(invoices))
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
                    _createInvoiceResult.postValue(CreateInvoiceResult(success = result.data))
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

    fun formDataChanged(name: String, godownId: String?, products: List<ProductData>) {
        if (!isNameValid(name)) {
            _invoiceForm.value = InvoiceFormState(customerError = R.string.invalid_customer_name)
        } else if (!isGodownValid(godownId)) {
            _invoiceForm.value = InvoiceFormState(godownError = R.string.invalid_godown_id)
        } else if (!isProductsValid(products)) {
            _invoiceForm.value = InvoiceFormState(godownError = R.string.invalid_invoice_products)
        } else {
            _invoiceForm.value = InvoiceFormState(isDataValid = true)
        }
    }

    private fun isGodownValid(godownId: String?): Boolean {
        return (godownId?.isNotBlank() == true && godownId != "-1")
    }

    private fun isNameValid(name: String): Boolean {
        return (name.isNotBlank())
    }

    private fun isProductsValid(products: List<ProductData>): Boolean {
        return (products.isNotEmpty())
    }
}