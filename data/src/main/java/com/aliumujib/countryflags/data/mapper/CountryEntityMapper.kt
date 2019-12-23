package com.aliumujib.countryflags.data.mapper

import com.aliumujib.countryflags.data.mapper.base.EntityMapper
import com.aliumujib.countryflags.data.model.CountryEntity
import com.aliumujib.countryflags.domain.models.Country
import javax.inject.Inject

class CountryEntityMapper @Inject constructor(
    private val currencyEntityMapper: CurrencyEntityMapper,
    private val languageEntityMapper: LanguageEntityMapper
) : EntityMapper<CountryEntity, Country>() {

    override fun mapFromEntity(entity: CountryEntity): Country {
        return Country(
            entity.alpha2Code,
            entity.alpha3Code,
            entity.area,
            entity.callingCodes,
            entity.capital,
            entity.cioc,
            entity.currencies.map {
                currencyEntityMapper.mapFromEntity(it)
            },
            entity.demonym,
            entity.flag,
            entity.languages.map {
                languageEntityMapper.mapFromEntity(it)
            },
            entity.latlng,
            entity.name,
            entity.nativeName,
            entity.numericCode,
            entity.population
        )
    }

    override fun mapToEntity(domain: Country): CountryEntity {
        return CountryEntity(
            domain.alpha2Code,
            domain.alpha3Code,
            domain.area,
            domain.callingCodes,
            domain.capital,
            domain.cioc,
            domain.currencies.map {
                currencyEntityMapper.mapToEntity(it)
            },
            domain.demonym,
            domain.flag,
            domain.languages.map {
                languageEntityMapper.mapToEntity(it)
            },
            domain.latlng,
            domain.name,
            domain.nativeName,
            domain.numericCode,
            domain.population
        )
    }

}
