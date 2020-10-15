package com.deepwares.fishmarketplace.model

data class Species(
    val name: Int,
    val image: Int,
    val status: Int,
    val minPrice: Int,
    val maxPrice: Int,
    val scientificName: Int,
    val commonName: Int,
    var desc: Int
) {
}