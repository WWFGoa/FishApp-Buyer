package com.deepwares.fishmarketplaceconsumer.ui.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R


class CreateViewModel : ViewModel() {

    val TAG = CreateViewModel::class.java.name

    val species = arrayListOf(

        Species("Anchovy", R.drawable.anchovy,"These are a small silvery fish that grow to a length of 12-15cm. They found in the Indo- Pacific region.They are a schooling species, occurring in coastal waters and are also known to enter estuaries. They feed mostly on zooplankton.  Nutritionally anchovies are an excellent source of calcium, iron and zinc."),
        Species("Barramundi", R.drawable.barramundi),
        Species("Base", R.drawable.basa),
        Species("Bombay Duck", R.drawable.bombay_duck,"Bombay duck despite its name, is not a duck but a lizardfish. Adult fish grow to a length of 25-40cm.  They inhabit the waters of the western Indo- Pacific region. They are mostly found in deep offshore waters but gather near rivers during the monsoons. These fish are marketed fresh, dried or salted. Bombay ducks are a good source of vitamin B6 and B3 (Niacin)."),
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