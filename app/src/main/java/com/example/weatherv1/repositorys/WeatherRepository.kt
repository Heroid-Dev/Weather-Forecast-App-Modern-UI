package com.example.weatherv1.repositorys

import android.content.Context
import com.example.weatherv1.data.local.FavoriteDao
import com.example.weatherv1.data.local.WeatherDao
import com.example.weatherv1.data.local.WeatherEntity
import com.example.weatherv1.data.remote.WeatherApi
import com.example.weatherv1.model.Weather
import com.example.weatherv1.utils.isInternetAvailable
import com.example.weatherv1.widgets.RequestState
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao,
    private val favoriteDao: FavoriteDao,
) {
    fun getWeather(
        city: String,
        fetchFromApi: Boolean,
        fetchOnlyFromApi: Boolean,
    ): Flow<RequestState<Weather>> = flow {
        //emit(RequestState.Loading)
        val localData = weatherDao.getWeatherData(city = city)
        val favoriteData = favoriteDao.getFavorite(city = city)
        if (fetchOnlyFromApi) {
            val remoteWeather = weatherApi.getWeather(city = city)
            emit(RequestState.Success(remoteWeather))
        } else {
            if (favoriteData != null && !fetchFromApi) {
                val weather = Gson().fromJson(favoriteData.weather, Weather::class.java)
                weatherDao.deleteAllWeatherData()
                weatherDao.insertWeather(
                    WeatherEntity(
                        city = city,
                        data = Gson().toJson(weather)
                    )
                )
                emit(RequestState.Success(weather))
            } else {
                if (fetchFromApi || localData == null) {
                    try {
                        val remoteWeather = weatherApi.getWeather(city = city)
                        weatherDao.deleteAllWeatherData()
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
    }
}

class WeatherRepository2 @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao,
    private val favoriteDao: FavoriteDao,
    @ApplicationContext private val context: Context,
) {
    fun getWeather(
        city: String,
        fetchFromApi: Boolean,
        fetchOnlyFromApi: Boolean,
    ): Flow<RequestState<Weather>> = flow {

        when (isInternetAvailable(context)) {
            true -> {
                if (fetchOnlyFromApi) {
                    val remoteWeather = weatherApi.getWeather(city = city)
                    emit(RequestState.Success(remoteWeather))
                } else {
                    try {
                        val remoteWeather = weatherApi.getWeather(city = city)
                        weatherDao.deleteAllWeatherData()
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
            }

            false -> {

                val localData = weatherDao.getWeatherData(city = city)
                val favoriteData = favoriteDao.getFavorite(city = city)
                if (!fetchFromApi && localData != null) {
                    val weather = Gson().fromJson(localData.data, Weather::class.java)
                    emit(RequestState.Success(weather))
                } else {
                    if (favoriteData != null && !fetchFromApi) {
                        val weather = Gson().fromJson(favoriteData.weather, Weather::class.java)
                        weatherDao.deleteAllWeatherData()
                        weatherDao.insertWeather(
                            WeatherEntity(
                                city = city,
                                data = Gson().toJson(weather)
                            )
                        )
                        emit(RequestState.Success(weather))
                    } else {
                        try {
                            val remoteWeather = weatherApi.getWeather(city = city)
                            weatherDao.deleteAllWeatherData()
                            weatherDao.insertWeather(
                                WeatherEntity(
                                    city = city,
                                    data = Gson().toJson(remoteWeather)
                                )
                            )
                            emit(RequestState.Success(remoteWeather))
                        }catch (e: HttpException){
                            emit(RequestState.Error(e))
                        }
                        catch (e: Exception) {
                            emit(RequestState.Error(e))
                        }
                    }
                }
            }
        }
    }
}