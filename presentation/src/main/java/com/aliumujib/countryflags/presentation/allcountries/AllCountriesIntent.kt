package com.aliumujib.countryflags.presentation.allcountries

import com.aliumujib.countryflags.presentation.mvibase.MVIIntent

sealed class AllCountriesIntent : MVIIntent {
    data class LoadAllCountriesIntent(val isOnline: Boolean) : AllCountriesIntent()
    data class RefreshAllCountriesIntent(val isOnline: Boolean) : AllCountriesIntent()
    data class SearchAllCountriesIntent(val query: String) : AllCountriesIntent()
}