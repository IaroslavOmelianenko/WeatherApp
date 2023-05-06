package com.github.iaroslavomelianenko.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.github.iaroslavomelianenko.weatherapp.data.db.CityDao
import com.github.iaroslavomelianenko.weatherapp.data.models.City

class CityRepository(private val cityDao: CityDao) {

    val readAllData: LiveData<List<City>> = cityDao.readAllData()

    suspend fun addCity (city: City){
        cityDao.addCity(city)
    }

    suspend fun updateCity(city: City){
        cityDao.updateCity(city)
    }
}