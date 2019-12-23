package com.aliumujib.countryflags.data.contracts

import com.aliumujib.countryflags.data.model.CountryEntity
import io.reactivex.Single

interface ICountriesRemote {

    fun fetchAllCountries(): Single<List<CountryEntity>>

}