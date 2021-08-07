package com.example.weatherapp.domain

import com.example.weatherapp.data.database.CityEntity
import com.example.weatherapp.data.model.ByLocationModel
import com.example.weatherapp.data.responseByLocation.ResponseByLocation

fun ResponseByLocation.toModel(): ByLocationModel {

    val base = base ?: ""
    val all = clouds?.all ?: 0
    val cod = cod ?: 0

    val lat = coord?.lat.toString()
    val lon = coord?.lon.toString()

    val dt = dt ?: 0
    val id = id ?: 0

    val feelsLike = main?.feelsLike.toString()
    val grndLevel = main?.grndLevel.toString()
    val humidity = main?.humidity.toString()
    val pressure = main?.pressure.toString()
    val seaLevel = main?.seaLevel.toString()
    val temp = main?.temp.toString()
    val tempMax = main?.tempMax.toString()
    val tempMin = main?.tempMin.toString()

    val name = name ?: ""

    val country = sys?.country ?: ""
    val countryId = sys?.id.toString()
    val sunrise = sys?.sunrise.toString()
    val sunset = sys?.sunset.toString()
    val type = sys?.type.toString()

    val timezone = timezone.toString()
    val visibility = visibility.toString()

    val description = weather?.first()?.description ?: ""
    val icon = weather?.first()?.icon ?: ""
    val weatherId = weather?.first()?.id.toString()
    val main = weather?.first()?.main ?: ""

    val deg = wind?.deg.toString()
    val gust = wind?.gust.toString()
    val speed = wind?.speed.toString()

    return ByLocationModel(
        base, all, cod,
        lat, lon, dt, id, feelsLike, grndLevel, humidity,
        pressure, seaLevel, temp, tempMax, tempMin, name, country,
        countryId, sunrise, sunset, type, timezone, visibility, description,
        icon, weatherId, main, deg, gust, speed
    )
}

fun ByLocationModel.toEntity() : CityEntity {

    return CityEntity(
        base, all, cod,
        lat, lon, dt, id, feelsLike, grndLevel, humidity,
        pressure, seaLevel, temp, tempMax, tempMin, name, country,
        countryId, sunrise, sunset, type, timezone, visibility, description,
        icon, weatherId, main, deg, gust, speed
    )

}
