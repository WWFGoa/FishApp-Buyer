package com.deepwares.fishmarketplace.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.Inventory
import com.amplifyframework.datastore.generated.model.Order
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.App
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository
import com.deepwares.fishmarketplaceconsumer.ui.orders.OrdersFragment
import com.deepwares.fishmarketplaceconsumer.ui.purchase.PurchaseFragmentArgs
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class MyOrderAdapter(var fragment: OrdersFragment?) : RecyclerView.Adapter<MyOrderVH>() {
    val species = ArrayList<Species>().apply { addAll(FishRepository.species) }
    val items = ArrayList<Order>()
    val dateTimeFormatter = DateTimeFormat.shortDateTime()
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

        vh.contact.setOnClickListener {
            val item = items[vh.adapterPosition]

            val location = item.inventory.contact
            fragment?.contact(location)

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
        holder.cost.setText(
            App.INSTANCE.getString(R.string.price_in_kg, item.price.toString())
        )
        holder.quantity.setText(
            App.INSTANCE.getString(
                R.string.qty_in_kg,
                order.quantity.toString()
            )
        )
        val seller = item.name?.trim()
        holder.seller.text = seller
        holder.name.setText(species.name)
        holder.kname.setText(species.konkaniName)
        holder.sizeType.setText("(" + item.size.name + ")")
        val time = item.createdAt.format()
        val dateTime = DateTime.parse(time)
        holder.time.setText(dateTimeFormatter.print(dateTime))
    }

}