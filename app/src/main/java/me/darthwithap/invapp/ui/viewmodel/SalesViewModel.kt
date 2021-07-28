package me.darthwithap.invapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.darthwithap.invapp.data.repository.SalesRepository
import me.darthwithap.invapp.ui.sales.result.SalesResult
import me.darthwithap.invapp.utils.Result

class SalesViewModel : ViewModel() {

    private var _salesResult: MutableLiveData<SalesResult> = MutableLiveData()
    val salesResult: LiveData<SalesResult> = _salesResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getSales() {
        viewModelScope.launch {
            when (val result = SalesRepository.getSales(true)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _salesResult.postValue(SalesResult(success = result.data))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _salesResult.postValue(SalesResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }

        }
    }
}