package me.darthwithap.invapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.darthwithap.api.models.requests.AddStockRequest
import me.darthwithap.invapp.R
import me.darthwithap.invapp.data.repository.StockRepository
import me.darthwithap.invapp.ui.addstock.godownstock.AddStockFormState
import me.darthwithap.invapp.ui.addstock.result.*
import me.darthwithap.invapp.utils.Result

private const val TAG = "StockViewModel"

class StockViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _addStockResult = MutableLiveData<AddStockResult>()
    val addStockResult: LiveData<AddStockResult> = _addStockResult

    private var _allStockResult = MutableLiveData<AllStockResult>()
    val allStockResult: LiveData<AllStockResult> = _allStockResult

    private var _godownStockResult = MutableLiveData<GodownStockResult>()
    val godownStockResult: LiveData<GodownStockResult> = _godownStockResult

    private var _stockHistoryResult = MutableLiveData<StockHistoryResult>()
    val stockHistoryResult: LiveData<StockHistoryResult> = _stockHistoryResult

    private var _searchStockResult = MutableLiveData<SearchStockResult>()
    val searchStockResult: LiveData<SearchStockResult> = _searchStockResult

    private val _formState = MutableLiveData<AddStockFormState>()
    val addStockFormState: LiveData<AddStockFormState> = _formState

    fun addStock(
        godownId: String,
        name: String,
        quantity: Int,
        brand: String?,
        code: String?,
        range: String?
    ) {
        Log.d(TAG, "addStock: brand: $brand")
        Log.d(TAG, "addStock: code: $code")
        Log.d(TAG, "addStock: range: $range")
        viewModelScope.launch {
            when (val result = StockRepository.addStock(
                AddStockRequest(
                    brand ?: "", code ?: "", godownId, name, quantity, range ?: ""
                )
            )) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _addStockResult.postValue(AddStockResult(success = result.data))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _addStockResult.postValue(AddStockResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun getAllStock() {
        viewModelScope.launch {
            when (val result = StockRepository.getAllStock(true)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _allStockResult.postValue(AllStockResult(success = result.data))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _allStockResult.postValue(AllStockResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun getGodownStock(godownId: String) {
        viewModelScope.launch {
            when (val result = StockRepository.getGodownStock(godownId, true)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _godownStockResult.postValue(GodownStockResult(success = result.data))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _godownStockResult.postValue(GodownStockResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun getStockHistory(stockId: String) {
        viewModelScope.launch {
            when (val result = StockRepository.getStockHistory(stockId, true)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _stockHistoryResult.postValue(StockHistoryResult(success = result.data))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _stockHistoryResult.postValue(StockHistoryResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun searchStock(query: String) {
        viewModelScope.launch {
            when (val result = StockRepository.searchStock(query, true)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _searchStockResult.postValue(SearchStockResult(success = result.data))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _searchStockResult.postValue(SearchStockResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun formDataChanged(name: String, qty: Int) {
        if (!isNameValid(name)) {
            _formState.value = AddStockFormState(nameError = R.string.invalid_product_name)
        } else if (!isQuantityValid(qty)) {
            _formState.value = AddStockFormState(quantityError = R.string.invalid_product_qty)
        } else {
            _formState.value = AddStockFormState(isDataValid = true)
        }
    }

    private fun isQuantityValid(quantity: Int): Boolean {
        return quantity > 0
    }

    private fun isNameValid(name: String): Boolean {
        return (name.isNotBlank())
    }
}