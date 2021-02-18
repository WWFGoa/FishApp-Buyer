package com.deepwares.fishmarketplaceconsumer.ui.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amplifyframework.datastore.generated.model.Inventory
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.App
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository


class CreateViewModel : ViewModel() {

    val TAG = CreateViewModel::class.java.name


    private val _species = MutableLiveData<List<Species>>().apply {
        value = ArrayList<Species>(FishRepository.species)
    }
    val speciesLiveData: MutableLiveData<List<Species>> = _species


    val speciesFiltered = ArrayList<Species>().apply { addAll(FishRepository.species) }

    private val _speciesFiltered = MutableLiveData<List<Species>>().apply {
        value = speciesFiltered
    }

    val speciesFilteredLiveData: MutableLiveData<List<Species>> = _speciesFiltered

    fun filter(name: String?) {

        var list = ArrayList<Species>()
        if (name.isNullOrEmpty()) {
            list.addAll(FishRepository.species)
        } else {
            FishRepository.species.forEach {

                if (App.INSTANCE.resources.getString(it.name).toLowerCase()
                        .contains(name.toLowerCase())
                    || name.toLowerCase().contains(
                        App.INSTANCE.resources.getString(it.name)
                            .toLowerCase()
                    )
                    || App.INSTANCE.resources.getString(it.name).toLowerCase()
                        .equals(name.toLowerCase())
                    || App.INSTANCE.resources.getString(it.konkaniName).toLowerCase()
                        .equals(name.toLowerCase())
                    || App.INSTANCE.resources.getString(it.commonName).toLowerCase()
                        .equals(name.toLowerCase())
                    || App.INSTANCE.resources.getString(it.konkaniName).toLowerCase()
                        .contains(name.toLowerCase())
                    || App.INSTANCE.resources.getString(it.commonName).toLowerCase()
                        .contains(name.toLowerCase())


                ) {
                    list.add(it)
                }
            }
        }
        speciesFilteredLiveData.postValue(list)
    }
}