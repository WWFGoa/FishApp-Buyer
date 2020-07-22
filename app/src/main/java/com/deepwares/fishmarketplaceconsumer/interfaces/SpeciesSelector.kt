package com.deepwares.fishmarketplace.interfaces

import com.deepwares.fishmarketplace.model.Species

interface SpeciesSelector {
    fun selectSpecies(species: Species, position:Int)
}