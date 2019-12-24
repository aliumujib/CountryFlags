package com.aliumujib.countries.remote.implementation

import com.aliumujib.countries.remote.api.CountriesAPI
import com.aliumujib.countries.remote.mapper.CountryRemoteModelMapper
import com.aliumujib.countryflags.data.contracts.ICountriesRemote
import com.aliumujib.countryflags.data.model.CountryEntity
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class CountriesRemoteImpl @Inject constructor(
    private val countriesAPI: CountriesAPI,
    private val countryRemoteModelMapper: CountryRemoteModelMapper
) : ICountriesRemote {

    override fun fetchAllCountries(): Maybe<List<CountryEntity>> {
        return countriesAPI.fetchAllCountries().map {
            countryRemoteModelMapper.mapModelList(it)
        }
    }

    override fun searchCountries(name: String): Maybe<List<CountryEntity>> {
        return countriesAPI.searchCountriesByName(name).map {
            countryRemoteModelMapper.mapModelList(it)
        }
    }

}