package com.aliumujib.countryflags.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COUNTRIES")
data class CountryCacheModel(
    @PrimaryKey
    val alpha2Code: String,
    val alpha3Code: String,
    val area: Double,
    val callingCodes: List<String>,
    val capital: String,
    val cioc: String,
    val currencies: List<CurrencyCacheModel>,
    val demonym: String,
    val flag: String,
    val languages: List<LanguageCacheModel>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int,
    val region: String,
    val subregion: String
)
