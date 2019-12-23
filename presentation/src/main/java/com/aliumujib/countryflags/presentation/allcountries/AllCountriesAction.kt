package com.aliumujib.countryflags.presentation.allcountries

sealed class AllCountriesAction {

    object LoadAllCountriesAction : AllCountriesAction()
    object RefreshAllCountriesAction : AllCountriesAction()

}