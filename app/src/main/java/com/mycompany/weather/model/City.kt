package com.mycompany.weather.model

data class City(val name: String, val lat: Double, val lon: Double) {
    var degree: Int? = null
}
