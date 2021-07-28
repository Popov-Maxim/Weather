package com.mycompany.weather.model

import com.mycompany.weather.model.City

interface Repository {
    fun getArrayCities():Array<City>
    fun changeCity(name:String)
    fun requestGet()
    fun getSelectedCity(): City?
    fun findACityByGeo()
}