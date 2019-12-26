package com.aliumujib.countryflags.models

import android.os.Parcelable
import com.aliumujib.countryflags.ui.adapters.allcountries.AllCountriesAdapterBindable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    val population: Int,
    val region: String,
    val subregion: String
) : AllCountriesAdapterBindable, Parcelable
