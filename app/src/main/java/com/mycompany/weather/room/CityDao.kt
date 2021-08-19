package com.mycompany.weather.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mycompany.weather.model.City

@Dao
interface CityDao {
    @Insert
    fun insert(vararg city: City)

    @Delete
    fun delete(city: City)

    @Query("SELECT * From City")
    fun getAll():List<City>
}