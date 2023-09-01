package com.example.jetmausam.model.geocoding

data class GeoCodingItem(
    val country: String,
    val lat: Double,
    val local_names: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)