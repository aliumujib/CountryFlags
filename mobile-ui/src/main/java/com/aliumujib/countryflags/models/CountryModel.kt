package com.aliumujib.countryflags.models

import com.aliumujib.countryflags.ui.adapters.allcountries.AllCountriesAdapterBindable

data class CountryModel(
    val alpha2Code: String,
    val alpha3Code: String,
    val area: Double,
    val callingCodes: List<String>,
    val capital: String,
    val cioc: String,
    val currencies: List<CurrencyModel>,
    val demonym: String,
    val flag: String,
    val languages: List<LanguageModel>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int
) : AllCountriesAdapterBindable
