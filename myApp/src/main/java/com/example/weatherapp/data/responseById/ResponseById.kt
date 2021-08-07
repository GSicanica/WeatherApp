package com.example.weatherapp.data.responseById

import com.google.gson.annotations.SerializedName

data class ResponseById(
    val city: City,
    val cnt: Int,
    val cod: String,
    @SerializedName("list")
    val allForecast: List<Forecast>,
    val message: Int
)