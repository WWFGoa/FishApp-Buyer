package com.deepwares.fishmarketplace.ui.home

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deepwares.fishmarketplaceconsumer.R
import kotlinx.android.synthetic.main.order_item.view.*

class MyOrderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val image = itemView.findViewById<ImageView>(R.id.image)
    val name = itemView.findViewById<TextView>(R.id.name)
    val quantity = itemView.findViewById<TextView>(R.id.quantity)
    val cost = itemView.findViewById<TextView>(R.id.cost)
    val seller = itemView.findViewById<TextView>(R.id.seller)
    val contact:Button = itemView.findViewById(R.id.contact)
    val directions:Button = itemView.findViewById(R.id.directions)

}