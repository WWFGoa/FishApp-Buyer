package com.deepwares.fishmarketplace.ui.creator

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.deepwares.fishmarketplaceconsumer.R
import kotlinx.android.synthetic.main.fish_species.view.*

class SpeciesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image)
    val name: TextView = itemView.findViewById(R.id.name)
    val localname: TextView = itemView.findViewById(R.id.local_name)
    val card = itemView as CardView
    val conservation = itemView.findViewById<View>(R.id.conservation_status)

}