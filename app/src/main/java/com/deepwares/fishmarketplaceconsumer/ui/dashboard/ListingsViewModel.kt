package com.deepwares.fishmarketplaceconsumer.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Refreshing the latest catches from fishermen in your area"
    }
    val text: LiveData<String> = _text
}