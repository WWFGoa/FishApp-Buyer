package com.deepwares.fishmarketplaceconsumer.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amplifyframework.api.ApiException
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.core.Amplify
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R


class CreateViewModel : ViewModel() {

    val TAG = CreateViewModel::class.java.name

    val species = arrayListOf(

        Species("Carp", R.drawable.bombay),
        Species("Sea Bass", R.drawable.anchovy),
        Species("Trout", R.drawable.baramundi),
        Species("Mackerel", R.drawable.bombay_duck),
        Species("Tuna", R.drawable.ic_asset_8),
        Species("Grouper", R.drawable.ic_asset_9),
        Species("Pomfret", R.drawable.ic_asset_10),
        Species("Cat Fish", R.drawable.ic_asset_11),
        Species("Lion Fish", R.drawable.ic_asset_12),
        Species("Cod", R.drawable.ic_asset_13),
        Species("European Sea Bass", R.drawable.ic_asset_14),
        Species("Halibut", R.drawable.ic_asset_15),
        Species("Mahi Mahi", R.drawable.ic_asset_16),
        Species("Pollock", R.drawable.ic_asset_17),
        Species("King Fish", R.drawable.ic_asset_18),
        Species("Tilapia", R.drawable.ic_asset_19),
        Species("Salmon", R.drawable.ic_asset_20),
        Species("Sturgeon", R.drawable.ic_asset_21),
        Species("Arctic Char", R.drawable.ic_asset_22),
        Species("Alaskan Salmon", R.drawable.ic_asset_23),
        Species("Snapper", R.drawable.ic_asset_24),
        Species("Dory", R.drawable.ic_asset_25),
        Species("Bream", R.drawable.ic_asset_26),
        Species("Haddock", R.drawable.ic_asset_27),
        Species("Drum", R.drawable.ic_asset_28),
        Species("Bream", R.drawable.ic_asset_29),
        Species("Flounder", R.drawable.ic_asset_30)
    )


    private val _species = MutableLiveData<List<Species>>().apply {
        value = species
    }
    val speciesLiveData: MutableLiveData<List<Species>> = _species


}