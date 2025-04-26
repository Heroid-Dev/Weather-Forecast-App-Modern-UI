package com.example.weatherv1.repositorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherv1.model.Weather
import com.example.weatherv1.widgets.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherStateFlow = MutableStateFlow<RequestState<Weather>>(RequestState.Idle)
    val weatherStateFlow = _weatherStateFlow.asStateFlow()

    fun getWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherStateFlow.value = RequestState.Loading
            delay(2000)
            try {
                weatherRepository.getWeather(city = city).collect {
                    _weatherStateFlow.value = it
                }
            }catch (e: Exception){
                _weatherStateFlow.value= RequestState.Error(e)
            }
        }
    }

}