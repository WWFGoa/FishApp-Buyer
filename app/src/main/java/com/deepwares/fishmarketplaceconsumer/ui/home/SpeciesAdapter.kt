package com.deepwares.fishmarketplace.ui.creator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deepwares.fishmarketplace.interfaces.SpeciesSelector
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R

class SpeciesAdapter(var speciesSelector: SpeciesSelector?) : RecyclerView.Adapter<SpeciesVH>() {
    val species = ArrayList<Species>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fish_species, parent, false)
        val vh = SpeciesVH(view)
        vh.itemView.setOnClickListener {
            val item = species[vh.adapterPosition]
            speciesSelector?.selectSpecies(item,vh.adapterPosition)
        }
        return vh

    }

    override fun getItemCount(): Int {
        return species.size
    }

    override fun onBindViewHolder(holder: SpeciesVH, position: Int) {
        val item = species[position]
        holder.image.setImageResource(item.image)
        holder.name.text = item.name
    }
}