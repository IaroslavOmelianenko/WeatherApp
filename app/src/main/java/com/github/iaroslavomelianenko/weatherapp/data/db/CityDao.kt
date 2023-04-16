package com.github.iaroslavomelianenko.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.iaroslavomelianenko.weatherapp.models.City
import com.github.iaroslavomelianenko.weatherapp.utils.DbConstants

@Dao
interface CityDao {
    @Query("SELECT * FROM ${DbConstants.TABLE_NAME}")
    fun readAllData(): LiveData<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(city: City)

    @Delete
    fun delete(city: City)
}