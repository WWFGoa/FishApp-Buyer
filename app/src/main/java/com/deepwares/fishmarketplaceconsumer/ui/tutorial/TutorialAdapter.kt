package com.deepwares.fishmarketplaceconsumer.ui.tutorial

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.deepwares.fishmarketplaceconsumer.MainActivity
import com.deepwares.fishmarketplaceconsumer.R
import java.util.*

class TutorialAdapter(
    diffCallback: DiffUtil.ItemCallback<Tutorial> = object : DiffUtil.ItemCallback<Tutorial>() {

        override fun areItemsTheSame(item1: Tutorial, item2: Tutorial): Boolean {
            return Objects.equals(item1.image, item2.image)
        }

        override fun areContentsTheSame(item1: Tutorial, item2: Tutorial): Boolean {
            return Objects.equals(item1.image, item2.image)
        }
    }
) :
    ListAdapter<Tutorial, TutorialVH>(diffCallback) {

    init {
        val list = arrayListOf(
            Tutorial(R.drawable.asset_1_tutorial, R.string.tutorial_desc_1),
            Tutorial(R.drawable.asset_2_tutorial, R.string.tutorial_desc_2),
            Tutorial(R.drawable.asset_3_tutorial, R.string.tutorial_desc_3),
            Tutorial(R.drawable.asset_4_tutorial, R.string.tutorial_desc_4),
            Tutorial(R.drawable.asset_5_tutorial, R.string.tutorial_desc_5),
            //Tutorial(R.drawable.asset_6_tutorial,R.string.tutorial_desc_6),
            Tutorial(R.drawable.asset_7_tutorial, R.string.tutorial_desc_7),
            Tutorial(R.drawable.asset_8_tutorial, R.string.tutorial_desc_8),
            Tutorial(R.drawable.asset_9_tutorial, R.string.tutorial_desc_9),
            Tutorial(R.drawable.asset_10_tutorial, R.string.tutorial_desc_10)
        )
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialVH {
        return TutorialVH(
            LayoutInflater.from(parent.context).inflate(R.layout.tutorial_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TutorialVH, position: Int) {
        val item = getItem(position)
        holder.image.setImageResource(item.image)
        holder.desc.setText(item.desc)
        val showContinue = position == currentList.size - 1
        holder.continueButton.visibility = if (showContinue) View.VISIBLE else View.GONE
        holder.continueButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            if(context is Activity){
                context.finish()
            }


        }
    }
}