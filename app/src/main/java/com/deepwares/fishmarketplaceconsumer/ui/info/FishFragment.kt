package com.deepwares.fishmarketplaceconsumer.ui.info

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository
import com.deepwares.fishmarketplaceconsumer.ui.catalog.CreateViewModel
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
        val status = resources.getInteger(species.status)
        when (status) {
            0 -> {
                conservation_status.setText(R.string.status_least_concern)
                conservation_status.setTextColor(Color.GREEN)
            }
            1 -> {
                conservation_status.setText(R.string.status_vulnerable)
                conservation_status.setTextColor(Color.YELLOW)
            }
            2 -> {
                conservation_status.setText(R.string.status_critical)
                conservation_status.setTextColor(Color.RED)
            }
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