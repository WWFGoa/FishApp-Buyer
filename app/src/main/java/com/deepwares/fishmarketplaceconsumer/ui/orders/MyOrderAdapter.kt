package com.deepwares.fishmarketplace.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.Inventory
import com.amplifyframework.datastore.generated.model.Order
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository
import com.deepwares.fishmarketplaceconsumer.ui.orders.OrdersFragment
import com.deepwares.fishmarketplaceconsumer.ui.purchase.PurchaseFragmentArgs

class MyOrderAdapter(var fragment: OrdersFragment?) : RecyclerView.Adapter<MyOrderVH>() {
    val species = ArrayList<Species>().apply { addAll(FishRepository.species) }
    val items = ArrayList<Order>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderVH {
        val vh =
            MyOrderVH(
                LayoutInflater.from(parent.context).inflate(R.layout.my_order_item, parent, false)
            )
        vh.directions.setOnClickListener {
            val item = items[vh.adapterPosition]

            val location = item.inventory.sellLocation
            fragment?.launchMaps(location)

        }
        vh.itemView.setOnClickListener {
            val item = items[vh.adapterPosition]

            val location = item.inventory.sellLocation
            fragment?.launchMaps(location)
        }
        return vh

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyOrderVH, position: Int) {
        val order = items[position]

        val item = order.inventory
        val species = species[item.species]
        holder.image.setImageResource(species.image)
        holder.cost.setText(item.price.toString())
        holder.quantity.setText(order.quantity.toString())
        holder.name.setText(species.name)

    }

}