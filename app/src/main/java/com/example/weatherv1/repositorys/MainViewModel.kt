package com.example.weatherv1.repositorys


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherv1.data.datastore.CityNameDataStore
import com.example.weatherv1.data.datastore.NotificationDataStore
import com.example.weatherv1.data.datastore.UnitPreference
import com.example.weatherv1.data.datastore.UnitsDataStore
import com.example.weatherv1.model.Weather
import com.example.weatherv1.widgets.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val geographicalRepository: GeographicalRepository,
    private val cityNameDataStore: CityNameDataStore,
    private val unitsDataStore: UnitsDataStore,
    private val notificationDataStore: NotificationDataStore
) : ViewModel() {

    private val _weatherStateFlow = MutableStateFlow<RequestState<Weather>>(RequestState.Idle)
    val weatherStateFlow = _weatherStateFlow.asStateFlow()

    fun getWeather(city: String, forceRefresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherStateFlow.value = RequestState.Loading
            delay(2000)
            try {
                weatherRepository.getWeather(city = city, fetchFromApi = forceRefresh).collect {
                    _weatherStateFlow.value = it
                }
            } catch (e: Exception) {
                _weatherStateFlow.value = RequestState.Error(e)
            }
        }
    }


    private val _currentCity = MutableStateFlow<String?>(null)
    val currentCity = _currentCity.asStateFlow()

    init {
        loadCityNameFromDataStore()
    }

    private fun loadCityNameFromDataStore() {
        viewModelScope.launch {
            cityNameDataStore.cityStatusFlow.collect {
                _currentCity.value = it
            }
        }
    }

    fun getCityNameFromApi(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val location = geographicalRepository.getGeographicalLocation(lat, lon)
                val city = location.address.county
                _currentCity.value = city
                cityNameDataStore.updateCityName(cityName = city)
            } catch (e: Exception) {
                _currentCity.value = e.message
            }
        }
    }


    fun updateCurrentCity(cityName: String) {
        viewModelScope.launch {
            cityNameDataStore.updateCityName(cityName)
            _currentCity.value = cityName
        }
    }

    val unitPrefs= unitsDataStore.unitsFlowState.stateIn(
        scope = viewModelScope,
        started =SharingStarted.WhileSubscribed(5000),
        initialValue = UnitPreference()
    )

    fun updateUnits(pref: UnitPreference){
        viewModelScope.launch(Dispatchers.IO) {
            unitsDataStore.updateUnits(pref = pref)
        }
    }

    val notificationEnabled=notificationDataStore.notificationEnabledFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    fun updateNotificationEnabled(enabled: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            notificationDataStore.updateNotificationPreference(enabled)
        }
    }

}