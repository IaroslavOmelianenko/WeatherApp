package com.github.iaroslavomelianenko.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.iaroslavomelianenko.weatherapp.data.models.City
import com.github.iaroslavomelianenko.weatherapp.utils.DbConstants

@Dao
interface CityDao {
    @Query("SELECT * FROM ${DbConstants.TABLE_NAME}")
    fun readAllData(): LiveData<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(city: City)

    @Update
    suspend fun updateCity(city: City)

    @Delete
    fun deleteCity(city: City)

    @Query("DELETE FROM ${DbConstants.TABLE_NAME}")
    suspend fun deleteAllCities()
}