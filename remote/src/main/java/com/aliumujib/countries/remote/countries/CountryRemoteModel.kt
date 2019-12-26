package com.aliumujib.countries.remote.countries

data class CountryRemoteModel(
    val alpha2Code: String,
    val alpha3Code: String,
    val altSpellings: List<String>,
    val area: Double?,
    val callingCodes: List<String>,
    val capital: String,
    val cioc: String?,
    val currencies: List<CurrencyRemoteModel>,
    val demonym: String?,
    val flag: String,
    val languages: List<LanguageRemoteModel>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String?,
    val numericCode: String?,
    val population: Int?,
    val region: String?,
    val subregion: String?
)
