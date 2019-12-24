package com.aliumujib.countryflags.cache.implementation

import com.aliumujib.countryflags.cache.mapper.CountryCacheModelMapper
import com.aliumujib.countryflags.cache.room.CountriesDao
import com.aliumujib.countryflags.data.contracts.ICountriesCache
import com.aliumujib.countryflags.data.model.CountryEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class CountriesCacheImpl @Inject constructor(
    private val countriesDao: CountriesDao,
    private val countryCacheModelMapper: CountryCacheModelMapper
) : ICountriesCache {

    override fun fetchCountries(): Maybe<List<CountryEntity>> {
        return countriesDao.getAllCountries().map {
            countryCacheModelMapper.mapFromEntityList(it)
        }
    }

    override fun saveCountries(countryEntity: List<CountryEntity>) {
        countriesDao.save(countryCacheModelMapper.mapToEntityList(countryEntity))
    }

}