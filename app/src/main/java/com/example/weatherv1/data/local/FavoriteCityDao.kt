package com.example.weatherv1.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.weatherv1.model.Weather
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCityDao {
    @Query("SELECT * FROM weather_table")
    fun getFavorites(): Flow<List<Weather>>



}