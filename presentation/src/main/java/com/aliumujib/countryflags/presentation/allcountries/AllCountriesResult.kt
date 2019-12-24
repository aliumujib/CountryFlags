package com.aliumujib.countryflags.presentation.allcountries

import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import com.aliumujib.countryflags.presentation.mvibase.MVIResult

sealed class AllCountriesResult : MVIResult {
    sealed class LoadAllCountriesResults() : AllCountriesResult() {
        data class Success(val data: List<CountryPresentationModel>) : LoadAllCountriesResults()
        object Loading : LoadAllCountriesResults()
        data class Error(val error: Throwable) : LoadAllCountriesResults()
    }

    sealed class SearchAllCountriesResults() : AllCountriesResult() {
        data class Success(val data: List<CountryPresentationModel>) : SearchAllCountriesResults()
        object Refreshing : SearchAllCountriesResults()
        data class Error(val error: Throwable) : SearchAllCountriesResults()
    }
}