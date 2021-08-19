package com.mycompany.weather.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycompany.weather.model.City
import com.mycompany.weather.model.MyModel
import com.mycompany.weather.model.Repository

class MyViewModelFactory(private val array: Array<City>) : ViewModelProvider.Factory {
    constructor() : this(arrayOf()) {

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(MutableLiveData<Repository?>(MyModel(array))) as T
    }
}