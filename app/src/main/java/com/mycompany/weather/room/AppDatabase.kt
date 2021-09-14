package com.mycompany.weather.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mycompany.weather.model.City

@Database(entities = [City::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}

const val NAME_DATABASE = "weather.db"