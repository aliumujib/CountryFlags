package com.aliumujib.countryflags.data.repositories

import com.aliumujib.countryflags.data.contracts.ICountriesCache
import com.aliumujib.countryflags.data.contracts.ICountriesRemote
import com.aliumujib.countryflags.data.mapper.CountryEntityMapper
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesRemote: ICountriesRemote,
    private val countryEntityMapper: CountryEntityMapper,
    private val countriesCache: ICountriesCache
) : ICountriesRepository {

    override fun fetchCountries(): Flowable<List<Country>> {
        return countriesCache.fetchCountries().map {
            countryEntityMapper.mapFromEntityList(it)
        }
    }

    override fun refreshCountries(): Completable {
        return countriesRemote.fetchAllCountries().map {
            countryEntityMapper.mapFromEntityList(it)
        }.doOnSuccess {
            countriesCache.saveCountries(countryEntityMapper.mapToEntityList(it))
        }.toCompletable()
    }

}