package com.aliumujib.countryflags.presentation.allcountries

import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import com.aliumujib.countryflags.presentation.mvibase.MVIViewState

sealed class AllCountriesViewState(
    val isLoading: Boolean,
    val data: List<CountryPresentationModel>,
    val error: Throwable?
) : MVIViewState {

    data class Success(val countries: List<CountryPresentationModel>) : AllCountriesViewState(
        false,
        countries,
        null
    )

    data class Error(val throwable: Throwable) :
        AllCountriesViewState(false, emptyList(), throwable)

    object Loading : AllCountriesViewState(true, emptyList(), null)

    object Idle : AllCountriesViewState(
        false,
        emptyList(),
        null
    )

}

