package com.example.weatherapp.data.database

import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DatabaseSource @Inject constructor(
    private val weatherDao: WeatherDao
) {

    fun insertCityWeather(city: CityEntity): Completable {
        return weatherDao.insertCityWeather(city)
    }

    fun addLocations(myLocationEntities: List<MyLocationEntity>) {
        return weatherDao.addLocations(myLocationEntities)
    }

    fun addLocation(myLocationEntity: MyLocationEntity) {
        return weatherDao.addLocation(myLocationEntity)
    }

    fun updateLocation(myLocationEntity: MyLocationEntity) {
        return weatherDao.updateLocation(myLocationEntity)
    }

    fun getLocations(): Observable<List<MyLocationEntity>> {
        return weatherDao.getLocations()
    }

}