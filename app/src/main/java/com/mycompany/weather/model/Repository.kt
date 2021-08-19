package com.mycompany.weather.model

import androidx.annotation.WorkerThread

interface CityRepository {
    val city: City?
    val cities: List<City>
    fun changeCity(name: String)
    fun findACityByGeo()
}

interface RequestRepository {
    fun requestGet()
}

interface DataBaseRepository {
    @WorkerThread
    fun loadCityFromDatabase()
}

interface MyRepository : CityRepository, RequestRepository, DataBaseRepository