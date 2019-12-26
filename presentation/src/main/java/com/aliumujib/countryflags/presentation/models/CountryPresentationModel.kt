package com.aliumujib.countryflags.presentation.models

data class CountryPresentationModel(
    val alpha2Code: String,
    val alpha3Code: String,
    val area: Double,
    val callingCodes: List<String>,
    val capital: String,
    val cioc: String,
    val currencies: List<CurrencyPresentationModel>,
    val demonym: String,
    val flag: String,
    val languages: List<LanguagePresentationModel>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int,
    val region: String,
    val subregion: String
)
