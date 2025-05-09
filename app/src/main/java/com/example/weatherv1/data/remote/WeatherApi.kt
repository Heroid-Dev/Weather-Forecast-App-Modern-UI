package com.example.weatherv1.data.remote


import com.example.weatherv1.model.Weather
import com.example.weatherv1.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET("VisualCrossingWebServices/rest/services/timeline/{city}/{days}")
    suspend fun getWeather(
        @Path("city") city: String,
        @Path("days") days: String="next7days",
        @Query("unitGroup") unit: String="metric",
        //@Query("include") include: String="hours%2Cfcst",
        @Query("key") key: String = Constants.API_KEY,
        @Query("contentType") contentType: String = "json"
    ): Weather
}