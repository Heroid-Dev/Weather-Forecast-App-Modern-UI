package com.example.weatherv1.di

import com.example.weatherv1.data.remote.GeographicalApi
import com.example.weatherv1.utils.Constants.NOMINATION_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GeographicalModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("User-Agent", "WeatherApp/1.0 (Legion5pro7@gmail.com)")
                    .build()
                chain.proceed(request)
            }
            .build()

    @Provides
    @Singleton
    fun provideNominationApi(okHttpClient: OkHttpClient): GeographicalApi =
        Retrofit
            .Builder()
            .baseUrl(NOMINATION_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeographicalApi::class.java)
}