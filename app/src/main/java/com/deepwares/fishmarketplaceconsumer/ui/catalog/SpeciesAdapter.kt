package com.deepwares.fishmarketplace.ui.creator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deepwares.fishmarketplace.interfaces.SpeciesSelector
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.App
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository

class SpeciesAdapter(var speciesSelector: SpeciesSelector?) : RecyclerView.Adapter<SpeciesVH>() {
    val species = ArrayList<Species>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fish_species, parent, false)
        val vh = SpeciesVH(view)
        vh.itemView.setOnClickListener {
            val item = species[vh.adapterPosition]
            val speciesPos = FishRepository.species.indexOf(item)
            speciesSelector?.selectSpecies(item, speciesPos)
        }
        return vh

    }

    override fun getItemCount(): Int {
        return species.size
    }

    override fun onBindViewHolder(holder: SpeciesVH, position: Int) {
        val item = species[position]
        holder.image.setImageResource(item.image)
        holder.name.setText(item.name)
        holder.localname.setText(item.konkaniName)
        val stat = App.INSTANCE.resources.getInteger(item.status)
        if (stat == 1) {
            holder.card.background =
                holder.image.resources.getDrawable(R.drawable.species_background_green_border)
        } else if (stat == 2) {
            holder.card.background =
                holder.image.resources.getDrawable(R.drawable.species_background_yellow_border)
        } else if (stat == 3) {
            holder.card.background =
                holder.image.resources.getDrawable(R.drawable.species_background_red_border)
        } else if (stat == 4) {
            holder.card.background =
                holder.image.resources.getDrawable(R.drawable.species_background_grey_border)
        } else {
            holder.card.background =
                holder.image.resources.getDrawable(R.drawable.species_background_blue_border)
        }

        /*
        if (position < itemCount / 3) {
            holder.conservation.setBackgroundDrawable(holder.image.resources.getDrawable(R.drawable.species_background_green_border))
        } else if (position < (itemCount * 2 / 3)) {
            holder.conservation.setBackgroundDrawable(holder.image.resources.getDrawable(R.drawable.species_background_yellow_border))

            //  holder.card.setCardBackgroundColor(holder.image.resources.getColor(R.color.species_background_yellow))
        } else {
            holder.conservation.setBackgroundDrawable(holder.image.resources.getDrawable(R.drawable.species_background_red_border))

            // holder.card.setCardBackgroundColor(holder.image.resources.getColor(R.color.species_background_red))

        }

         */
    }
}