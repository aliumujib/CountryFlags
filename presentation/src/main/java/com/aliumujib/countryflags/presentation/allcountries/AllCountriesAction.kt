package com.aliumujib.countryflags.presentation.allcountries

sealed class AllCountriesAction {

    data class LoadAllCountriesAction(val isConnected: Boolean) : AllCountriesAction()
    data class SearchAllCountriesAction(val query: String) : AllCountriesAction()

}