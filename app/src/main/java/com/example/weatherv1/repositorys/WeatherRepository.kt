package com.example.weatherv1.repositorys

import com.example.weatherv1.model.Weather
import com.example.weatherv1.nertwork.WeatherApi
import com.example.weatherv1.widgets.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {
    fun getWeather(city: String): Flow<RequestState<Weather>> = flow {
        try{
           emit(RequestState.Success(weatherApi.getWeather(city = city)))
        }catch (e: Exception){
           emit( RequestState.Error(e))
        }
    }
}