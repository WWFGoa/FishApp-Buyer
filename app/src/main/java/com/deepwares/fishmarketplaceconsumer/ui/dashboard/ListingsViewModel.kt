package com.deepwares.fishmarketplaceconsumer.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Inventory

class ListingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Refreshing the latest catches from fishermen in your area"
    }
    val text: LiveData<String> = _text

    val items = MutableLiveData<List<Inventory>>()
    val TAG = ListingsViewModel::class.java.name
    fun fetch() {

        Amplify.API.query(
            ModelQuery.list(Inventory::class.java),
            { response ->
                response?.data?.let {
                    val newitems = ArrayList<Inventory>()
                    newitems.addAll(it)
                    items.postValue(newitems)

                }
                Log.d(TAG, "Got items : " + response.data?.toString())

            },
            { error -> Log.e(TAG, "Get inventory listtt failed", error) }


        )
    }
}