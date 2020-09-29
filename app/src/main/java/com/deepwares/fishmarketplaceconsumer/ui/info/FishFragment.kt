package com.deepwares.fishmarketplaceconsumer.ui.info

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
        species = createViewModel.species[args.image]
        return inflater.inflate(R.layout.fish_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FishViewModel::class.java)
        image.setImageResource(species.image)
        name.setText(species.name)
        desc.text =
            "Grouper is a lean, moist fish with a distinctive yet mild flavor, large flakes and a firm texture. The Red Grouper has a slightly sweeter, milder flavor than the Black Grouper and is considered to be the better of the two. Grouper's flavor profile is like a cross between Bass and Halibut.\n"
        recipes_1.setOnClickListener {
            val args = RecipeFragmentArgs("https://www.carolinescooking.com/persian-style-marinated-fish/").toBundle()
            findNavController().navigate(R.id.info_to_recipe, args)
        }
        recipes_2.setOnClickListener {
            val args = RecipeFragmentArgs("https://www.carolinescooking.com/goan-fish-curry/").toBundle()
            findNavController().navigate(R.id.info_to_recipe, args)
        }

    }

}