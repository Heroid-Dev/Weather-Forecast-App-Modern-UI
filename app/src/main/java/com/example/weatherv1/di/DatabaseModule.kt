package com.example.weatherv1.di

import android.content.Context
import androidx.room.Room
import com.example.weatherv1.data.local.FavoriteDao
import com.example.weatherv1.data.local.FavoritesDatabase
import com.example.weatherv1.data.local.WeatherDao
import com.example.weatherv1.data.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context = context,
            klass = WeatherDatabase::class.java,
            name = "WeatherDatabase"
        ).build()

    @Singleton
    @Provides
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao = database.weatherDao()


    @Provides
    @Singleton
    fun provideFavoritesDatabase(@ApplicationContext context: Context): FavoritesDatabase =
        Room.databaseBuilder(
            context = context,
            klass = FavoritesDatabase::class.java,
            name = "FavoriteDatabase"
        ).build()

    @Provides
    @Singleton
    fun provideFavoriteDao(favoritesDatabase: FavoritesDatabase): FavoriteDao= favoritesDatabase.favoriteDao()
}