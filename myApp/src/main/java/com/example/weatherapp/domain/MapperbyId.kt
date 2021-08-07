package com.example.weatherapp.domain

import com.example.weatherapp.data.database.ForecastEntity
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.responseById.Forecast

fun Forecast.toModel(): ForecastModel {

    val dtTxt = dtTxt
    val icon = weather[0].icon
    val description = weather[0].description
    val temp = main.temp
    val tempMin = main.temp_min.toString()
    val tempMax = main.temp_max.toString()
    val humidity = main.humidity.toString()
    val pressure = main.pressure.toString()
    val speed = wind.speed.toString()

    return ForecastModel(dtTxt, icon, description, temp,tempMin, tempMax, humidity, pressure, speed)
}

fun ForecastModel.toEntity() : ForecastEntity {
    return ForecastEntity(dtTxt, icon, description, temp,tempMin, tempMax, humidity, pressure, speed)

}

