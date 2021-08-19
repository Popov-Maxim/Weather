package com.mycompany.weather.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mycompany.weather.model.City

@Database(entities = [City::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}

const val NAME_DATABASE = "weather.db"
val MY_MIGRATION = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
    }
}