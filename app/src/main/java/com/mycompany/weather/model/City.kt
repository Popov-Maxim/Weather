package com.mycompany.weather.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class City(val name: String, val lat: Double, val lon: Double) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Ignore
    var degree: Int? = null
}
