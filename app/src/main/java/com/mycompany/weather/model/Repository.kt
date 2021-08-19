package com.mycompany.weather.model

import com.mycompany.weather.model.City

interface CityRepository {
    fun getArrayCities(): Array<City>
    fun changeCity(name: String)
    fun getSelectedCity(): City?
    fun findACityByGeo()
}

interface RequestRepository {
    fun requestGet()
}

interface DataBaseRepository {

}

interface MyRepository : CityRepository, RequestRepository, DataBaseRepository