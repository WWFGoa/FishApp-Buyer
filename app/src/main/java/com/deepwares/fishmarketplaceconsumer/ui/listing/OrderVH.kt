package com.deepwares.fishmarketplace.ui.home

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deepwares.fishmarketplaceconsumer.R
import kotlinx.android.synthetic.main.order_item.view.*

class OrderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val image = itemView.findViewById<ImageView>(R.id.image)
    val name = itemView.findViewById<TextView>(R.id.name)
    val kname = itemView.findViewById<TextView>(R.id.kname)
    val quantity = itemView.findViewById<TextView>(R.id.quantity)
    val cost = itemView.findViewById<TextView>(R.id.cost)
    val seller = itemView.findViewById<TextView>(R.id.seller)
    val order:Button = itemView.findViewById(R.id.order)
    val soldOut:View = itemView.findViewById(R.id.sold_out)
    val soldOutOverlay:View = itemView.findViewById(R.id.sold_out_overlay)
    val sizeType = itemView.findViewById<TextView>(R.id.size_type)
    val catchTime = itemView.findViewById<TextView>(R.id.time)
    val catchLocation = itemView.findViewById<TextView>(R.id.location)
}