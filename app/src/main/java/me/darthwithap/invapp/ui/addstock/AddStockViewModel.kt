package me.darthwithap.invapp.ui.addstock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddStockViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Add Stock Fragment"
    }
    val text: LiveData<String> = _text
}