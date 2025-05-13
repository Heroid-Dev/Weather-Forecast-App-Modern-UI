package com.example.weatherv1.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

data class UnitPreference(
    val isFahrenheit: Boolean = false,
    val isMph: Boolean = false,
    val inKPa: Boolean = false,
    val inRain_mm: Boolean=false
)

val Context.unitsPreference by preferencesDataStore("units_preferences")

class UnitsDataStore(
    private val context: Context
){
    private val myPreference= context.unitsPreference

    companion object PreferenceKeys{
        val IS_FAHRENHEIT = booleanPreferencesKey("is_fahrenheit")
        val IS_MPH = booleanPreferencesKey("is_mph")
        val IS_KPa = booleanPreferencesKey("is_inhg")
        val Rain_IN_MM=booleanPreferencesKey("Rain_in_mm")
    }

    val unitsFlowState = myPreference.data
        .catch { exception->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }.map { preference->
            UnitPreference(
                isFahrenheit = preference[PreferenceKeys.IS_FAHRENHEIT] == true,
                isMph = preference[PreferenceKeys.IS_MPH] == true,
                inKPa = preference[PreferenceKeys.IS_KPa] == true,
                inRain_mm = preference[PreferenceKeys.Rain_IN_MM]==true
            )
        }

    suspend fun updateUnits(pref: UnitPreference){
        myPreference.edit { preference->
            preference[PreferenceKeys.IS_FAHRENHEIT]= pref.isFahrenheit
            preference[PreferenceKeys.IS_MPH]= pref.isMph
            preference[PreferenceKeys.IS_KPa] = pref.inKPa
            preference[PreferenceKeys.Rain_IN_MM]=pref.inRain_mm
        }
    }

}
