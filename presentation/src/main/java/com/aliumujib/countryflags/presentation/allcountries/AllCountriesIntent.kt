package com.aliumujib.countryflags.presentation.allcountries

import com.aliumujib.countryflags.presentation.mvibase.MVIIntent

sealed class AllCountriesIntent : MVIIntent {
    object LoadAllCountriesIntent : AllCountriesIntent()
    object RefreshAllCountriesIntent : AllCountriesIntent()
}