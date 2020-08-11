package com.deepwares.fishmarketplace.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.Inventory
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R

class OrderAdapter : RecyclerView.Adapter<OrderVH>() {
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
        //,
        // Species("Dory", R.drawable.ic_asset_25),
        // Species("Bream", R.drawable.ic_asset_26),
        // Species("Haddock", R.drawable.ic_asset_27),
        // Species("Drum", R.drawable.ic_asset_28),
        // Species("Bream", R.drawable.ic_asset_29),
        // Species("Flounder", R.drawable.ic_asset_30)
    )
    val items = ArrayList<Inventory>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderVH {
        val vh =
            OrderVH(LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OrderVH, position: Int) {
        val item = items[position]
        val species = species[item.species]
        holder.image.setImageResource(species.image)
        holder.cost.setText(item.price.toString())
        holder.quantity.setText(item.quantity.toString())
        holder.name.setText(species.name)

    }

}