package com.aliumujib.countryflags.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aliumujib.countryflags.cache.converters.Converters
import com.aliumujib.countryflags.cache.models.CountryCacheModel


@Database(
    entities = [
        CountryCacheModel::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    Converters::class
)
abstract class CoutriesDB : RoomDatabase() {

    abstract fun countriesDao(): CountriesDao

}