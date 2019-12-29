package com.aliumujib.countryflags.cache.implementation

import com.aliumujib.countryflags.cache.mapper.CountryCacheModelMapper
import com.aliumujib.countryflags.cache.room.CountriesDao
import com.aliumujib.countryflags.data.contracts.ICountriesCache
import com.aliumujib.countryflags.data.model.CountryEntity
import io.reactivex.Maybe
import javax.inject.Inject

class CountriesCacheImpl @Inject constructor(
    private val countriesDao: CountriesDao,
    private val countryCacheModelMapper: CountryCacheModelMapper
) : ICountriesCache {

    override fun fetchAllCountries(): Maybe<List<CountryEntity>> {
        return countriesDao.getAllCountries().map {
            countryCacheModelMapper.mapFromEntityList(it)
        }
    }

    override fun saveCountries(countryEntity: List<CountryEntity>) {
        countriesDao.save(countryCacheModelMapper.mapToEntityList(countryEntity))
    }

}