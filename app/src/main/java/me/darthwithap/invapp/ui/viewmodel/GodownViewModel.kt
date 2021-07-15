package me.darthwithap.invapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.darthwithap.api.models.requests.NewGodownRequest
import me.darthwithap.invapp.data.godown.GodownRepository
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.ui.addstock.GodownsResult
import me.darthwithap.invapp.ui.addstock.NewGodownResult
import me.darthwithap.invapp.utils.Result

class GodownViewModel : ViewModel() {

    private var _currentGodown: MutableLiveData<Godown> = MutableLiveData()
    private val currentGodown: LiveData<Godown> = _currentGodown

    private val godownId: MutableLiveData<String> = MutableLiveData()

    private val _godownsResult = MutableLiveData<GodownsResult>()
    val godownsResult: LiveData<GodownsResult> = _godownsResult

    private val _newGodownResult = MutableLiveData<NewGodownResult>()
    val newGodownResult: LiveData<NewGodownResult> = _newGodownResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Todo chip Implementation from recipe app
    // Todo pass godown order here!

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

//    fun getPendingOrdersForGodown() {
//        viewModelScope.launch {
//            when (val result = GodownRepository.getPendingOrdersForGodown(true)) {
//                is Result.Success -> {
//                    _isLoading.postValue(false)
//                    _godownsResult.postValue(GodownsResult(success = result.data))
//                }
//                is Result.Error -> {
//                    _isLoading.postValue(false)
//                    _godownsResult.postValue(GodownsResult(error = result.exception.message))
//                }
//                is Result.Loading -> _isLoading.postValue(true)
//            }
//        }
//    }

    fun createGodown(godownName: String) {
        viewModelScope.launch {
            when (val result = GodownRepository.createGodown(NewGodownRequest(godownName), true)) {
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

    fun onGodownChanged(q: String) {
        setGodownId(q)
    }

    fun onCurrentGodownChanged(godownId: String) {
        // Todo OnGodownId Changed
    }

    private fun setGodownId(gid: String) {
        godownId.value = gid
        //savedStateHandle.set(STATE_KEY_GODOWN_ID, gid
    }

    private fun clearCurrentGodown() {
        setCurrentGodown(null)
    }

    private fun setCurrentGodown(godown: Godown?) {
        _currentGodown.value = godown
        //savedStateHandle.set(STATE_KEY_RECIPE_LIST_SELECTED_CATEGORY, foodCategory)
    }

}