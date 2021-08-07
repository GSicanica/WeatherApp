package com.example.weatherapp.data.responseById

data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    var temp: String,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)