package com.example.weatherv1.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.cityPreferences by preferencesDataStore("city-preferences")

class CityNameDataStore @Inject constructor(
    private val context: Context
) {
    val cityPreferences = context.cityPreferences

    companion object PreferencesKeys{
        val CITY_NAME = stringPreferencesKey("city_name")
    }

    val cityStatusFlow = cityPreferences.data
        .catch {exception->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }.map { preferences->
            val city=preferences[PreferencesKeys.CITY_NAME]
            city
        }

    suspend fun updateCityName(cityName: String){
        cityPreferences.edit { preferences->
            preferences[PreferencesKeys.CITY_NAME]=cityName
        }
    }

}