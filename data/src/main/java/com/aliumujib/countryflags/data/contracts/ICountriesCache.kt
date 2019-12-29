package com.aliumujib.countryflags.data.contracts

import com.aliumujib.countryflags.data.model.CountryEntity
import io.reactivex.Maybe

interface ICountriesCache {

    fun fetchAllCountries(): Maybe<List<CountryEntity>>

    fun saveCountries(countryEntity: List<CountryEntity>)

}