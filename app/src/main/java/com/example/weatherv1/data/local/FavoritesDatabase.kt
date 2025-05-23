package com.example.weatherv1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherv1.utils.WeatherConverter

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
@TypeConverters(WeatherConverter::class)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}