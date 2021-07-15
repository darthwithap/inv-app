package me.darthwithap.invapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.darthwithap.invapp.data.invoice.InvoiceRepository
import me.darthwithap.invapp.data.stock.StockRepository
import me.darthwithap.invapp.ui.orders.PendingOrdersResult
import me.darthwithap.invapp.utils.Result

class StockViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _pendingOrdersResult = MutableLiveData<PendingOrdersResult>()
    val pendingOrdersResult: LiveData<PendingOrdersResult> = _pendingOrdersResult

    private var _currentGodownId: MutableLiveData<String> = MutableLiveData()
    val currentGodownId: LiveData<String> = _currentGodownId
    private var _currentGodownName: MutableLiveData<String> = MutableLiveData()
    val currentGodownName: LiveData<String> = _currentGodownName

    fun setCurrentGodownId(godownId: String) {
        _currentGodownId.postValue(godownId)
    }

    fun setCurrentGodownName(name: String) {
        _currentGodownName.postValue(name)
    }


//    fun addStock(godownId: String) {
//        viewModelScope.launch {
//            when (val result = StockRepository.addStock()) {
//                is Result.Success -> {
//                    _isLoading.postValue(false)
//                    _pendingOrdersResult.postValue(PendingOrdersResult(result.data))
//                }
//                is Result.Error -> {
//                    _isLoading.postValue(false)
//                    _pendingOrdersResult.postValue(PendingOrdersResult(error = result.exception.message))
//                }
//                is Result.Loading -> _isLoading.postValue(true)
//            }
//        }
//    }

//    fun getAllStock() {
//        viewModelScope.launch {
//            when (val result = InvoiceRepository.createInvoice()) {
//                is Result.Success -> {
//                    _isLoading.postValue(false)
//                    _pendingOrdersResult.postValue(PendingOrdersResult(result.data))
//                }
//                is Result.Error -> {
//                    _isLoading.postValue(false)
//                    _pendingOrdersResult.postValue(PendingOrdersResult(error = result.exception.message))
//                }
//                is Result.Loading -> _isLoading.postValue(true)
//            }
//        }
//    }

    fun getGodownStock(godownId: String) {
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

    fun getStockHistory(godownId: String) {
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

    fun searchStock(godownId: String) {
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
}