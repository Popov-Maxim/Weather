package com.mycompany.weather.model

import com.mycompany.weather.model.City

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

}

interface MyRepository : CityRepository, RequestRepository, DataBaseRepository