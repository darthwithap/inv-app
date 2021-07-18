package me.darthwithap.invapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.darthwithap.api.models.requests.NewGodownRequest
import me.darthwithap.api.models.requests.PendingOrdersUpdateRequest
import me.darthwithap.invapp.data.godown.GodownRepository
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.data.invoice.InvoiceRepository
import me.darthwithap.invapp.ui.addstock.result.GodownsResult
import me.darthwithap.invapp.ui.addstock.NewGodownResult
import me.darthwithap.invapp.ui.orders.result.PendingOrdersResult
import me.darthwithap.invapp.ui.orders.result.PendingOrdersUpdateStatusResult
import me.darthwithap.invapp.utils.Result

class GodownViewModel : ViewModel() {

    private val _currGodownId: MutableLiveData<String> = MutableLiveData()
    val currGodownId: LiveData<String> = _currGodownId

    private val _godownsResult = MutableLiveData<GodownsResult>()
    val godownsResult: LiveData<GodownsResult> = _godownsResult

    private val _newGodownResult = MutableLiveData<NewGodownResult>()
    val newGodownResult: LiveData<NewGodownResult> = _newGodownResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllGodowns() {
        viewModelScope.launch {
            when (val result = GodownRepository.getAllGodowns(true)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _godownsResult.postValue(GodownsResult(success = result.data))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _godownsResult.postValue(GodownsResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun createGodown(godownName: String) {
        viewModelScope.launch {
            when (val result = GodownRepository.createGodown(NewGodownRequest(godownName))) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _newGodownResult.postValue(NewGodownResult(success = result.data))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _newGodownResult.postValue(NewGodownResult(error = result.exception.message))
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun onCurrentGodownChanged(godownId: String) {
        setGodownId(godownId)
    }

    private fun setGodownId(gid: String) {
        _currGodownId.value = gid
        //savedStateHandle.set(STATE_KEY_GODOWN_ID, gid)
    }

}