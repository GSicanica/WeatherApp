package com.example.weatherapp.data.networking

import com.example.weatherapp.data.responseById.ResponseById
import com.example.weatherapp.data.responseByLocation.ResponseByLocation
import com.example.weatherapp.utils.API_KEY
import io.reactivex.Observable
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val weatherApi: WeatherApi
) {
    fun searchByCity(cityName: String): Observable<ResponseByLocation> {
        return weatherApi.searchByCityNameToGetID(cityName, API_KEY)
    }

    fun searchWithID(id: String): Observable<ResponseById> {
        return weatherApi.searchWithID(id, API_KEY)
    }

    fun searchByCurrentLocation(lat: Double, long: Double): Observable<ResponseByLocation> {
        return weatherApi.searchByLatLon(lat, long, API_KEY)
    }
}