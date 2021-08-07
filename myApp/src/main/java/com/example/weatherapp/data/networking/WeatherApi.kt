package com.example.weatherapp.data.networking

import com.example.weatherapp.data.responseById.ResponseById
import com.example.weatherapp.data.responseByLocation.ResponseByLocation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    fun searchByCityNameToGetID(
        @Query("q") q: String,
        @Query("appid") appId: String
    ) : Observable<ResponseByLocation>

    @GET("data/2.5/weather")
    fun searchByLatLon(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String
    ) : Observable<ResponseByLocation>

    @GET("data/2.5/forecast")
    fun searchWithID(
        @Query("id") id: String,
        @Query("appid") appId: String
    ) : Observable<ResponseById>

}
