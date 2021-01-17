package com.deepwares.fishmarketplace.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.Inventory
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.App
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository
import com.deepwares.fishmarketplaceconsumer.ui.purchase.PurchaseFragmentArgs

class OrderAdapter : RecyclerView.Adapter<OrderVH>() {
    val species = ArrayList<Species>().apply { addAll(FishRepository.species) }
    val items = ArrayList<Inventory>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderVH {
        val vh =
            OrderVH(LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false))
        vh.order.setOnClickListener {
            val item = items[vh.adapterPosition]

            val bundle = PurchaseFragmentArgs(vh.adapterPosition).toBundle()
            vh.itemView.findNavController().navigate(R.id.navigation_purchase, bundle)

        }
        vh.itemView.setOnClickListener {
            val item = items[vh.adapterPosition]
            val available = item.availableQuantity > 0f
            if (available) {
                val bundle = PurchaseFragmentArgs(vh.adapterPosition).toBundle()
                vh.itemView.findNavController().navigate(R.id.navigation_purchase, bundle)
            }

        }
        return vh

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OrderVH, position: Int) {
        val item = items[position]
        val species = species[item.species]
        holder.image.setImageResource(species.image)
        holder.cost.setText(
            App.INSTANCE.getString(R.string.price_in_kg, item.price.toString())
        )
        holder.quantity.setText(
            App.INSTANCE.getString(
                R.string.qty_in_kg,
                item.availableQuantity.toString()
            )
        )
        holder.seller.text = item.name
        holder.name.setText(species.name)
        val available = item.availableQuantity > 0f
        holder.order.visibility = if (available) View.VISIBLE else View.GONE
        holder.soldOut.visibility = if (!available) View.VISIBLE else View.GONE
        holder.soldOutOverlay.visibility = if (!available) View.VISIBLE else View.GONE
        (holder.itemView as CardView).foreground = if (!available) App.getInstance()
            .getDrawable(R.color.black_5_pct) else App.getInstance()
            .getDrawable(R.color.transparent)

        holder.sizeType.setText("(" + item.size.name + ")")
    }

}