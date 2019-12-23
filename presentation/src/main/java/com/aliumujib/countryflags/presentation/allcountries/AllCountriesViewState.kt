package com.aliumujib.countryflags.presentation.allcountries

import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import com.aliumujib.countryflags.presentation.mvibase.MVIViewState

data class AllCountriesViewState(
    val isLoading: Boolean,
    val data: List<CountryPresentationModel>,
    val error: Throwable?
) : MVIViewState {
    companion object {
        fun idle() = AllCountriesViewState(
                false,
                emptyList(),
                null
        )
    }
}