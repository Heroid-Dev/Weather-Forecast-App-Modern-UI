package com.example.weatherv1.widgets

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.weatherv1.MainActivity
import com.example.weatherv1.R

fun showNotification(
    context: Context,
    title: String,
    message: String,
    smallIcon: Int
) {
    val intent = Intent(context, MainActivity::class.java)
    val notificationPendingIntent = PendingIntent.getActivity(
        context, 1, intent, PendingIntent.FLAG_IMMUTABLE
    )
    val notification = NotificationCompat.Builder(context, "weather_notification")
        .setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(smallIcon)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setOngoing(true)
        .setContentIntent(notificationPendingIntent)
        .build()
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(1, notification)
}

fun cancelNotification(context: Context){
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancel(1)
}