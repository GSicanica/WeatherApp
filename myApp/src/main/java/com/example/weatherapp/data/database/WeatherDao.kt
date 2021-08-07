package com.example.weatherapp.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

/**
 * @author lllhr
 * @date 5/27/2021
 */

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCityWeather(city: CityEntity): Completable

    @Query("SELECT * FROM my_location_table ORDER BY date DESC")
    fun getLocations(): Observable<List<MyLocationEntity>>

    @Query("SELECT * FROM my_location_table WHERE id=:id")
    fun getLocation(id: UUID): Observable<MyLocationEntity>

    @Update
    fun updateLocation(myLocationEntity: MyLocationEntity)

    @Insert
    fun addLocation(myLocationEntity: MyLocationEntity)

    @Insert
    fun addLocations(myLocationEntities: List<MyLocationEntity>)
}