package com.example.weatherapp.di

import com.example.weatherapp.data.database.DatabaseSource
import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.networking.NetworkDataSource
import com.example.weatherapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ProvideRepository {

    @Provides
    @Singleton
    fun provideRep(
        databdatabaseSource: DatabaseSource,
        weatherApi: NetworkDataSource
    ): Repository {

        return Repository(databdatabaseSource, weatherApi)
    }
}