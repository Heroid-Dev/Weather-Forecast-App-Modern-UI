package com.example.weatherv1.repositorys

import com.example.weatherv1.model.GeographicalLocation
import com.example.weatherv1.data.remote.GeographicalApi
import javax.inject.Inject

class GeographicalRepository @Inject constructor(
    private val geographicalApi: GeographicalApi
) {
    suspend fun getGeographicalLocation(
        lat: Double,
        lon: Double
    ): GeographicalLocation = geographicalApi.cityGeographicalLocation(latitude = lat, longitude = lon)

}

