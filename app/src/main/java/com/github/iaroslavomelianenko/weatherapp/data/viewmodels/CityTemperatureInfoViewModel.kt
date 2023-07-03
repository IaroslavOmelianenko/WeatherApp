package com.github.iaroslavomelianenko.weatherapp.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.iaroslavomelianenko.weatherapp.utils.Season
import com.github.iaroslavomelianenko.weatherapp.utils.TemperatureScale

class CityTemperatureInfoViewModel: ViewModel(){
    private var _season = MutableLiveData(Season.WINTER)
    private var _temperatureScale = MutableLiveData(TemperatureScale.CELSIUS)
    val season: LiveData<Season> = _season
    val temperatureScale: LiveData<TemperatureScale> = _temperatureScale

    fun setSeason(season: Season){
        _season.value = season
    }

    fun getSeason(): Season? {
        return _season.value
    }

    fun setTemperatureScale(temperatureScale: TemperatureScale){
        _temperatureScale.value = temperatureScale
    }

    fun getTemperatureScale(): TemperatureScale? {
        return _temperatureScale.value
    }

}