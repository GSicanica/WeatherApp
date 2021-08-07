package com.example.weatherapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_city_table")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    var base: String,
    var all: Int,
    var cod: Int,
    var lat: String,
    var lon: String,
    var dt: Int,
    var id: Int,
    var feelsLike: String,
    var grndLevel: String,
    var humidity: String,
    var pressure: String,
    var seaLevel: String,
    var temp: String,
    var tempMax: String,
    var tempMin: String,
    var name: String,
    var country: String,
    var countryId: String,
    var sunrise: String,
    var sunset: String,
    var type: String,
    var timezone: String,
    var visibility: String,
    var description: String,
    var icon: String,
    var weatherId: String,
    var main: String,
    var deg: String,
    var gust: String,
    var speed: String
)
