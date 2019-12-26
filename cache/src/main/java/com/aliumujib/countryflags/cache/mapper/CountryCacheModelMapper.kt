package com.aliumujib.countryflags.cache.mapper

import com.aliumujib.countryflags.cache.models.CountryCacheModel
import com.aliumujib.countryflags.data.mapper.base.EntityMapper
import com.aliumujib.countryflags.data.model.CountryEntity
import javax.inject.Inject

class CountryCacheModelMapper @Inject constructor(
    private val currencyEntityMapper: CurrencyCacheModelMapper,
    private val languageEntityMapper: LanguageCacheModelMapper
) : EntityMapper<CountryCacheModel, CountryEntity>() {

    override fun mapFromEntity(entity: CountryCacheModel): CountryEntity {
        return CountryEntity(
            entity.alpha2Code,
            entity.alpha3Code,
            entity.area,
            entity.callingCodes,
            entity.capital,
            entity.cioc,
            entity.currencies.map {
                currencyEntityMapper.mapFromModel(it)
            },
            entity.demonym,
            entity.flag,
            entity.languages.map {
                languageEntityMapper.mapFromModel(it)
            },
            entity.latlng,
            entity.name,
            entity.nativeName,
            entity.numericCode,
            entity.population,
            entity.region,
            entity.subregion
        )
    }

    override fun mapToEntity(domain: CountryEntity): CountryCacheModel {
        return CountryCacheModel(
            domain.alpha2Code,
            domain.alpha3Code,
            domain.area,
            domain.callingCodes,
            domain.capital,
            domain.cioc,
            domain.currencies.map {
                currencyEntityMapper.mapToModel(it)
            },
            domain.demonym,
            domain.flag,
            domain.languages.map {
                languageEntityMapper.mapToModel(it)
            },
            domain.latlng,
            domain.name,
            domain.nativeName,
            domain.numericCode,
            domain.population,
            domain.region,
            domain.subregion
        )
    }

}
