package com.aliumujib.countryflags.data.contracts

import com.aliumujib.countryflags.data.model.CountryEntity
import io.reactivex.Flowable

interface ICountriesCache {

    fun fetchCountries(): Flowable<List<CountryEntity>>

    fun saveCountries(countryEntity: List<CountryEntity>)

}