package me.darthwithap.invapp.ui.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeliveryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Delivery Fragment"
    }
    val text: LiveData<String> = _text
}