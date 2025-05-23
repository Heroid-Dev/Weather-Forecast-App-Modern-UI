package com.example.weatherv1

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            "weather_notification",
            "Weather Notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}