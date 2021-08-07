package com.example.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CityEntity::class, MyLocationEntity::class], version = 1, exportSchema = true)
@TypeConverters(MyLocationTypeConverters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
