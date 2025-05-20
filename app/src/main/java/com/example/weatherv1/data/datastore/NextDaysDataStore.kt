package com.example.weatherv1.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException




val Context.nextDaysDataStore by preferencesDataStore("next days datastore")

class NextDaysDataStore(
    private val context: Context,
) {
    private val nextDayPreferences = context.nextDaysDataStore

    companion object PreferenceKeys {
        val NEXT_DAYS_STATE = stringPreferencesKey("next days state")
    }

    val nextDaysFlow = nextDayPreferences.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            NextDaysState.valueOf(
                preferences[PreferenceKeys.NEXT_DAYS_STATE]
                    ?: NextDaysState.next7days.name
            )
        }

    suspend fun updateNextDaysState(state: NextDaysState) {
        nextDayPreferences.edit { preferences ->
            preferences[PreferenceKeys.NEXT_DAYS_STATE] = state.name
        }
    }
}

enum class NextDaysState {
    next7days,
    next15days
}