package com.github.iaroslavomelianenko.weatherapp.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.iaroslavomelianenko.weatherapp.utils.Season

class CityTemperatureInfoViewModel: ViewModel(){
    private var _season = MutableLiveData(Season.WINTER)
    val season: LiveData<Season> = _season

    fun setSeason(season: Season){
        _season.value = season
    }

    fun getSeason(): Season? {
        return _season.value
    }

}