package com.example.weatherapp.domain

import com.example.weatherapp.data.database.CityEntity
import com.example.weatherapp.data.database.DatabaseSource
import com.example.weatherapp.data.database.MyLocationEntity
import com.example.weatherapp.data.networking.NetworkDataSource
import com.example.weatherapp.data.responseById.ResponseById
import com.example.weatherapp.data.responseByLocation.ResponseByLocation
import io.reactivex.Observable
import java.util.concurrent.Executors

class Repository (
    private val  databdatabaseSource: DatabaseSource,
    private val weatherApi : NetworkDataSource,
) {

    private val executor = Executors.newSingleThreadExecutor()

    fun searchByCurrentLocation(lati : Double, longi: Double): Observable<ResponseByLocation> {
        return weatherApi.searchByCurrentLocation(lati, longi)
    }

    fun searchByCity(name : String): Observable<ResponseByLocation> {
        return weatherApi.searchByCity(name)
    }

    fun searchWithID(id : String): Observable<ResponseById> {
        return weatherApi.searchWithID(id)
    }

    fun getLocations() = databdatabaseSource.getLocations()

    //fun getLocation(id: UUID) = weatherDao.getLocation(id)

    fun updateLocation(myLocationEntity: MyLocationEntity) {
        executor.execute {
            databdatabaseSource.updateLocation(myLocationEntity)
        }
    }

    fun addLocation(myLocationEntity: MyLocationEntity) {
        executor.execute {
            databdatabaseSource.addLocation(myLocationEntity)
        }
    }

    fun insertCityWeather(cityEntity: CityEntity) {
        executor.execute {
            databdatabaseSource.insertCityWeather(cityEntity)
        }
    }

    fun addLocations(myLocationEntities: List<MyLocationEntity>) {
        executor.execute {
            databdatabaseSource.addLocations(myLocationEntities)
        }
    }

}