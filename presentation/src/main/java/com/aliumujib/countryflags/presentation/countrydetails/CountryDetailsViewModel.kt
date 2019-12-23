package com.aliumujib.countryflags.presentation.countrydetails

import androidx.lifecycle.ViewModel
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesResult.*
import com.aliumujib.countryflags.presentation.mvibase.MVIViewModel
import io.reactivex.Observable
import java.util.function.BiFunction
import javax.inject.Inject

class CountryDetailsViewModel @Inject constructor() : ViewModel()