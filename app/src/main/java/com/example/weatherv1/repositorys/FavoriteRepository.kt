package com.example.weatherv1.repositorys

import android.content.Context
import android.util.Log
import com.example.weatherv1.data.local.FavoriteDao
import com.example.weatherv1.data.local.FavoriteEntity
import com.example.weatherv1.data.remote.WeatherApi
import com.example.weatherv1.model.Weather
import com.example.weatherv1.utils.isInternetAvailable
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val weatherApi: WeatherApi,
    @ApplicationContext private val context: Context,
) {

    fun getAllFavorites(): Flow<List<Weather>> = favoriteDao.getAllFavorites().map { list ->
        list.map { Gson().fromJson(it.weather, Weather::class.java) }
    }

    suspend fun addToFavorites(favoriteEntity: FavoriteEntity) =
        favoriteDao.insertToFavorite(favoriteEntity)

    suspend fun deleteAllFavorites() = favoriteDao.deleteAllFavorites()

    suspend fun getFavorite(city: String): FavoriteEntity? = favoriteDao.getFavorite(city = city)

    suspend fun deleteFromFavorite(favoriteEntity: FavoriteEntity) =
        favoriteDao.deleteFavorite(favoriteEntity = favoriteEntity)

    suspend fun refreshAllFavorites() {
        if (isInternetAvailable(context)) {
            favoriteDao.getAllFavorites().first().forEach { favorite ->
                try {
                    val weather = weatherApi.getWeather(city = favorite.city)
                    addToFavorites(
                        favoriteEntity = FavoriteEntity(
                            city = favorite.city,
                            weather = Gson().toJson(weather)
                        )
                    )
                } catch (e: Exception) {
                    Log.e("FavoriteRepository", "Error to give information of ${favorite.city}", e)
                }
            }
        }
    }


}