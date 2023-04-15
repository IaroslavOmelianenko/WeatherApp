package com.github.iaroslavomelianenko.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.iaroslavomelianenko.weatherapp.models.City

@Dao
interface CityDao {
    @Query("SELECT * FROM city_temperature")
    fun readAllData(): LiveData<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(city: City)

    @Delete
    fun delete(city: City)
}