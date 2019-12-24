package com.aliumujib.countryflags.data.contracts

import com.aliumujib.countryflags.data.model.CountryEntity
import io.reactivex.Maybe
import io.reactivex.Single

interface ICountriesRemote {

    fun fetchAllCountries(): Maybe<List<CountryEntity>>

    fun searchCountries(name: String): Maybe<List<CountryEntity>>

}