package com.example.weatherv1.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather_table WHERE city= :city")
    suspend fun getWeatherData(city: String): WeatherEntity?

    @Query("DELETE FROM weather_table")
    suspend fun deleteAllWeatherData()
}