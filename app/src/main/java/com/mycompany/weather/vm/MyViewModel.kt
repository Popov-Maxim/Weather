package com.mycompany.weather.vm

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycompany.weather.model.City
import com.mycompany.weather.model.MyRepository
import kotlin.concurrent.thread

class MyViewModel(private val _model: MutableLiveData<MyRepository?>) : ViewModel() {
    val model: LiveData<MyRepository?> = _model

    fun changeCity(name: String) {
        _model.value?.changeCity(name)
    }

    fun getArrayCities(): Array<City> {
        return _model.value?.cities?.toTypedArray() ?: arrayOf()
    }

    fun requestGet() {
        thread {
            _model.value?.requestGet()
            Handler(Looper.getMainLooper()).post {
                _model.value = _model.value
            }
        }
//        _model.value = _model.value.also { it?.requestGet() }
    }

    fun loadCityFromDatabase() {
        _model.value?.loadCityFromDatabase()
    }
}