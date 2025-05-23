package com.example.weatherv1.utils

import androidx.room.TypeConverter
import com.example.weatherv1.model.Weather
import com.google.gson.Gson

class WeatherConverter {
    @TypeConverter
    fun fromWeather(weather: Weather): String =
        Gson().toJson(weather)
    @TypeConverter
    fun toWeather(json: String): Weather =
        Gson().fromJson(json, Weather::class.java)
}