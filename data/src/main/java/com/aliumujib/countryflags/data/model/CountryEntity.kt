package com.aliumujib.countryflags.data.model

data class CountryEntity(
    val alpha2Code: String,
    val alpha3Code: String,
    val area: Double,
    val callingCodes: List<String>,
    val capital: String,
    val cioc: String,
    val currencies: List<CurrencyEntity>,
    val demonym: String,
    val flag: String,
    val languages: List<LanguageEntity>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int,
    val region: String,
    val subregion: String
)
