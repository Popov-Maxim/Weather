package com.mycompany.weather.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.mycompany.weather.model.City
import com.mycompany.weather.model.MyModel
import com.mycompany.weather.model.MyRepository
import com.mycompany.weather.room.AppDatabase
import com.mycompany.weather.room.MY_MIGRATION
import com.mycompany.weather.room.NAME_DATABASE

class MyViewModelFactory(private val array: Array<City>, private val applicationContext: Context) :
    ViewModelProvider.Factory {
    constructor(applicationContext: Context) : this(arrayOf(), applicationContext) {

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(
            MutableLiveData<MyRepository?>(

                MyModel(
                    array,
                    Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        NAME_DATABASE
                    ).addMigrations(MY_MIGRATION).build()
                )
            )
        ) as T
    }
}