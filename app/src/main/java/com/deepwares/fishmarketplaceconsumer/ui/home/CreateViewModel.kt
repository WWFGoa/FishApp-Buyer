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

        Species("Anchovy", R.drawable.anchovy),
        Species("Barramundi", R.drawable.barramundi),
        Species("Base", R.drawable.basa),
        Species("Bombay Duck", R.drawable.bombay_duck),
        Species("Ghole", R.drawable.ghole),
        Species("Grouper", R.drawable.grouper),
        Species("Kane", R.drawable.kane),
        Species("Kingfish", R.drawable.kingfish),
        Species("Mahi Mahi", R.drawable.mahi_mahi),
        Species("Mullet", R.drawable.mullet),
        Species("Pearl Spot", R.drawable.pearl_spot),
        Species("Pomfret", R.drawable.pomfret),
        Species("Red Snapper", R.drawable.red_snapper),
        Species("Rohu", R.drawable.rohu),
        Species("Salmon", R.drawable.salmon),
        Species("Sea Veral", R.drawable.sea_veral),
        Species("Seer", R.drawable.seer),
        Species("Shrimp", R.drawable.shrimp),
        Species("Snake head murrel", R.drawable.snake_head_murrel),
        Species("Sole", R.drawable.sole),
        Species("Tuna", R.drawable.tuna)
    )


    private val _species = MutableLiveData<List<Species>>().apply {
        value = species
    }
    val speciesLiveData: MutableLiveData<List<Species>> = _species


}