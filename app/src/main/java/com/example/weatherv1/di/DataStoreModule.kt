package com.example.weatherv1.di

import android.content.Context
import com.example.weatherv1.data.datastore.CityNameDataStore
import com.example.weatherv1.data.datastore.NotificationDataStore
import com.example.weatherv1.data.datastore.UnitsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideCityNameDataStore(@ApplicationContext context: Context): CityNameDataStore =
        CityNameDataStore(context)

    @Provides
    @Singleton
    fun provideUnitsDatabase(@ApplicationContext context: Context): UnitsDataStore =
        UnitsDataStore(context)

    @Provides
    @Singleton
    fun provideNotificationDataStore(@ApplicationContext context: Context): NotificationDataStore =
        NotificationDataStore(context)

}
