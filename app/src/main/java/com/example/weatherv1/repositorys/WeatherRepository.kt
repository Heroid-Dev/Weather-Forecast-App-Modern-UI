package com.example.weatherv1.repositorys

import com.example.weatherv1.data.local.WeatherDao
import com.example.weatherv1.data.local.WeatherEntity
import com.example.weatherv1.model.Weather
import com.example.weatherv1.data.remote.WeatherApi
import com.example.weatherv1.widgets.RequestState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao,
) {
    fun getWeather(city: String, fetchFromApi: Boolean): Flow<RequestState<Weather>> = flow {
        //emit(RequestState.Loading)
        val localData = weatherDao.getWeatherData(city = city)
        if (fetchFromApi || localData == null) {
            try {
                val remoteWeather = weatherApi.getWeather(city = city)
                weatherDao.insertWeather(
                    WeatherEntity(
                        city = city,
                        data = Gson().toJson(remoteWeather)
                    )
                )
                emit(RequestState.Success(remoteWeather))
            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
        if (!fetchFromApi && localData != null) {
            val weather = Gson().fromJson(localData.data, Weather::class.java)
            emit(RequestState.Success(weather))
        }
    }


}