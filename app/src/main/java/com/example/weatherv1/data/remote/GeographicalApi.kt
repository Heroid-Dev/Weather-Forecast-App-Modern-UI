package com.example.weatherv1.data.remote


import com.example.weatherv1.model.GeographicalLocation
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface GeographicalApi {
    @GET("reverse")
    suspend fun cityGeographicalLocation(
        @Query("format") format: String="json",
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("accept-language") language: String = "en"
    ): GeographicalLocation
}