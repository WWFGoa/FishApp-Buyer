package com.deepwares.fishmarketplaceconsumer.ui.orders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Inventory
import com.amplifyframework.datastore.generated.model.Order
import com.deepwares.fishmarketplaceconsumer.ui.listing.ListingsViewModel
import org.joda.time.DateTime

class OrdersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text


    val items = MutableLiveData<List<Order>>()
    val TAG = ListingsViewModel::class.java.name
    fun fetch() {
        Amplify.API.query(
            ModelQuery.list(
                Order::class.java,
                Order.USER_ID.eq(Amplify.Auth.currentUser.userId)
                    .and(Order.CREATED_AT.gt(DateTime.now().minusDays(1).toDate()))
                //, Order.CREATED_AT.gt(DateTime.now().minusDays(7).toDate())
            ),
            { response ->
                response?.data?.let {
                    val newitems = ArrayList<Order>()
                    val filter = it.filter { it.userId == Amplify.Auth.currentUser.userId }
                    if (!filter.isNullOrEmpty()) {
                        // newitems.addAll(filter)
                    }
                    newitems.addAll(it.sortedByDescending { it.createdAt })
                    items.postValue(newitems)
                }
                Log.d(TAG, "Got items : " + response.data?.toString())

            },
            { error -> Log.e(TAG, "Get order list failed from AWS", error) }
        )
    }

}