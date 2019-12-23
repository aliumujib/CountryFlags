package com.aliumujib.countryflags.domain.repositories.countries

import com.aliumujib.countryflags.domain.models.Country
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


interface ICountriesRepository {

    fun fetchCountries(): Flowable<List<Country>>

    fun refreshCountries(): Completable


}