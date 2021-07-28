package com.mycompany.weather.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycompany.weather.model.Repository
import com.mycompany.weather.model.City
import com.mycompany.weather.model.MyModel

class MyViewModel : ViewModel() {
    private val _model = MutableLiveData<Repository?>(null)
    val model: LiveData<Repository?> = _model

    fun setModel(array: Array<City>) {
        _model.value ?: { _model.value = MyModel(array) }()
    }

    fun changeCity(name: String) {
        _model.value?.changeCity(name)
    }

    fun getArrayCities(): Array<City>? {
        return _model.value?.getArrayCities()
    }

    fun requestGet() {
        _model.value = _model.value?.also { it.requestGet() }
    }
}