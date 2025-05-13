package com.example.weatherv1.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.notificationPreference by preferencesDataStore("notification preference")

class NotificationDataStore(
    private val context: Context,
) {
    private val notificationPreference = context.notificationPreference

    companion object PreferenceKey {
        val NOTIFICATION_ENABLED = booleanPreferencesKey("notification_enabled")
    }

    val notificationEnabledFlow = notificationPreference.data
        .catch { exception -> if (exception is IOException) emit(emptyPreferences()) else throw exception }
        .map { preferences ->
            preferences[PreferenceKey.NOTIFICATION_ENABLED] == true
        }

    suspend fun updateNotificationPreference(enabled: Boolean){
        notificationPreference.edit { preference->
            preference[PreferenceKey.NOTIFICATION_ENABLED]= enabled
        }
    }

}