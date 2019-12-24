package com.aliumujib.countryflags.data.repositories

import com.aliumujib.countryflags.data.contracts.ICountriesCache
import com.aliumujib.countryflags.data.contracts.ICountriesRemote
import com.aliumujib.countryflags.data.mapper.CountryEntityMapper
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import io.reactivex.Maybe
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesRemote: ICountriesRemote,
    private val countryEntityMapper: CountryEntityMapper,
    private val countriesCache: ICountriesCache
) : ICountriesRepository {

    override fun fetchCountries(connected: Boolean): Maybe<List<Country>> {
        return if (connected) {
            countriesRemote.fetchAllCountries().map {
                countryEntityMapper.mapFromEntityList(it)
            }.doOnSuccess {
                countriesCache.saveCountries(countryEntityMapper.mapToEntityList(it))
            }
        } else {
            countriesCache.fetchCountries().map {
                countryEntityMapper.mapFromEntityList(it)
            }
        }
    }

    override fun searchCountries(query: String): Maybe<List<Country>> {
        return countriesRemote.searchCountries(query).map {
            countryEntityMapper.mapFromEntityList(it)
        }
    }

}