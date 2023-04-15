package com.github.iaroslavomelianenko.weatherapp.data.db

import androidx.lifecycle.LiveData
import com.github.iaroslavomelianenko.weatherapp.models.City

class CityRepository(private val cityDao: CityDao) {

    val readAllData: LiveData<List<City>> = cityDao.readAllData()

    suspend fun addCity (city: City){
        cityDao.addCity(city)
    }
}