package com.example.weatherapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_forecast_table")
class ForecastEntity (
    @PrimaryKey(autoGenerate = false)
    val dtTxt : String,
    val icon: String,
    val description: String,
    val temp: String,
    val tempMin: String,
    val tempMax: String,
    val humidity: String,
    val pressure: String,
    val speed: String)