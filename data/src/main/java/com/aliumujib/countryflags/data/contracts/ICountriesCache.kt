package com.aliumujib.countryflags.data.contracts

import com.aliumujib.countryflags.data.model.CountryEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface ICountriesCache {

    fun fetchCountries(): Maybe<List<CountryEntity>>

    fun saveCountries(countryEntity: List<CountryEntity>)

}