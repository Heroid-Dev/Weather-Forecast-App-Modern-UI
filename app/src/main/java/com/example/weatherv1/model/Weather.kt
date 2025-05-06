package com.example.weatherv1.model

data class Weather(
    val address: String,
    val days: List<Day>,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val queryCost: Int,
    val resolvedAddress: String,
    val timezone: String,
    val tzoffset: Double
)