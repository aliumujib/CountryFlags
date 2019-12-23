package com.aliumujib.countryflags.domain.models

data class Country(
    val alpha2Code: String,
    val alpha3Code: String,
    val area: Double,
    val callingCodes: List<String>,
    val capital: String,
    val cioc: String,
    val currencies: List<Currency>,
    val demonym: String,
    val flag: String,
    val languages: List<Language>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int
)
