package com.deepwares.fishmarketplaceconsumer.ui.info

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository
import com.deepwares.fishmarketplaceconsumer.ui.catalog.CreateViewModel
import com.deepwares.fishmarketplaceconsumer.ui.listing.FilteredListingFragmentArgs
import com.deepwares.fishmarketplaceconsumer.ui.recipe.RecipeFragmentArgs
import kotlinx.android.synthetic.main.fish_fragment.*

class FishFragment : Fragment() {

    val args: FishFragmentArgs by navArgs()
    private lateinit var createViewModel: CreateViewModel

    companion object {
        fun newInstance() = FishFragment()
    }

    private lateinit var viewModel: FishViewModel
    lateinit var species: Species

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createViewModel =
            ViewModelProvider(requireActivity()).get(CreateViewModel::class.java)
        species = FishRepository.species[args.image]
        return inflater.inflate(R.layout.fish_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FishViewModel::class.java)
        image.setImageResource(species.image)
        name.setText(species.name)
        desc.setText(species.desc)
        common.setText(species.commonName)
        scientific.setText(species.scientificName)
        konkani.setText(species.konkaniName)
        val status = resources.getInteger(species.status)
        var allowed = true
        when (status) {
            1 -> {
                conservation_status.setText(R.string.status_adbundant)
                conservation_status.setTextColor(resources.getColor(R.color.species_background_green))
            }
            2 -> {
                conservation_status.setText(R.string.status_declining)
                conservation_status.setTextColor(resources.getColor(R.color.species_background_yellow))
            }
            3 -> {
                conservation_status.setText(R.string.status_depleted)
                conservation_status.setTextColor(resources.getColor(R.color.species_background_red))
            }
            4 -> {
                allowed = false
                conservation_status.setText(R.string.status_banned)
                availability.setText(R.string.unavailable)
                conservation_status.setTextColor(resources.getColor(R.color.species_background_grey))
            }
            else -> {
                conservation_status.setText(R.string.status_not_major_fishery)
                conservation_status.setTextColor(resources.getColor(R.color.species_background_blue))
            }
        }

        availability.setOnClickListener {
            if (allowed) {
                checkAvailability()
            } else {
                Toast.makeText(
                    context,
                    "Sorry this species is prohibited from consumption",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }

    private fun checkAvailability() {
        if (true) {

            findNavController().navigate(
                R.id.navigation_listings_filtered,
                FilteredListingFragmentArgs(
                    args.image,
                    resources.getString(species.name)
                ).toBundle()
            )
        } else {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*
        recipes_1.setOnClickListener {
            val args =
                RecipeFragmentArgs("https://www.carolinescooking.com/persian-style-marinated-fish/").toBundle()
            findNavController().navigate(R.id.info_to_recipe, args)
        }
        recipes_2.setOnClickListener {
            val args =
                RecipeFragmentArgs("https://www.carolinescooking.com/goan-fish-curry/").toBundle()
            findNavController().navigate(R.id.info_to_recipe, args)
        }

         */

    }

}