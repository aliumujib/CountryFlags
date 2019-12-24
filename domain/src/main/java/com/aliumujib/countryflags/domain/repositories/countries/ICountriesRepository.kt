package com.aliumujib.countryflags.domain.repositories.countries

import com.aliumujib.countryflags.domain.models.Country
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single


interface ICountriesRepository {

    fun fetchCountries(connected: Boolean): Maybe<List<Country>>

    fun searchCountries(query: String): Maybe<List<Country>>


}