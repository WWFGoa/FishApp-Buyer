package com.deepwares.fishmarketplaceconsumer.ui.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository


class CreateViewModel : ViewModel() {

    val TAG = CreateViewModel::class.java.name


    private val _species = MutableLiveData<List<Species>>().apply {
        value = ArrayList<Species>(FishRepository.species)
    }
    val speciesLiveData: MutableLiveData<List<Species>> = _species


}