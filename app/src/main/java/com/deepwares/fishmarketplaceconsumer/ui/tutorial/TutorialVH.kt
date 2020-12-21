package com.deepwares.fishmarketplaceconsumer.ui.tutorial

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deepwares.fishmarketplaceconsumer.R
import kotlinx.android.synthetic.main.tutorial_item.view.*

class TutorialVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image)
    val desc: TextView = itemView.findViewById(R.id.desc)
    val continueButton: Button = itemView.findViewById(R.id.continueButton)
}