package com.deepwares.fishmarketplaceconsumer.ui.listing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Inventory
import org.joda.time.DateTime
import kotlin.collections.ArrayList

class ListingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Refreshing the latest catches from fishermen in your area"
    }
    val text: LiveData<String> = _text

    val items = MutableLiveData<List<Inventory>>()
    val TAG = ListingsViewModel::class.java.name
    fun fetch() {
        val query = ModelQuery.list(
            Inventory::class.java,
            Inventory.CREATED_AT.gt(DateTime.now().minusHours(6).toDate())
        )
        Amplify.API.query(
            query,
            { response ->
                response?.data?.let {
                    val newitems = ArrayList<Inventory>()
                    it?.forEach {
                        val createdAt = it.createdAt.toDate()
                        val expired = createdAt.before(DateTime.now().minusHours(6).toDate())
                        if (!expired) {
                            // newitems.add(it)
                        }
                    }
                    newitems.addAll(it.sortedByDescending { it.createdAt })
                    items.postValue(newitems)

                }
                Log.d(TAG, "Got items : " + response.data?.toString())

            },
            { error -> Log.e(TAG, "Get inventory list failed from AWS", error) }


        )
    }
}