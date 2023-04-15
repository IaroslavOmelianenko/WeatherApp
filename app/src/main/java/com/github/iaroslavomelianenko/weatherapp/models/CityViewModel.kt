package com.github.iaroslavomelianenko.weatherapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.github.iaroslavomelianenko.weatherapp.data.db.CityRepository
import com.github.iaroslavomelianenko.weatherapp.data.db.CityTemperatureDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<City>>
    private val repository: CityRepository

    init {  //init block will be first executed when this vm is called
        val cityDao = CityTemperatureDb.getDatabase(application).cityDao()
        repository = CityRepository(cityDao)
        readAllData = repository.readAllData
    }

    fun addCity(city: City){
        viewModelScope.launch(Dispatchers.IO) {
            //Dispatchers.Io means that this code will be running in the background thread
            repository.addCity(city)
        }
    }
}