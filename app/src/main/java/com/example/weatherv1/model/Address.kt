package com.example.weatherv1.model

data class Address(
    val ISO3166_2_lvl4: String,
    val country: String,
    val country_code: String,
    val county: String,
    val district: String,
    val postcode: String,
    val road: String,
    val state: String,
    val town: String
)