package com.aliumujib.countryflags.presentation.mappers

import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import javax.inject.Inject

open class CountryModelPresentationMapper @Inject constructor(
    var languageModelPresentationMapper: LanguageModelPresentationMapper,
    var currencyModelMapper: CurrencyModelPresentationMapper
) :
    PresentationMapper<Country, CountryPresentationModel> {

    override fun mapToPresentation(domain: Country): CountryPresentationModel {
        return CountryPresentationModel(
            domain.alpha2Code,
            domain.alpha3Code,
            domain.area,
            domain.callingCodes,
            domain.capital,
            domain.cioc,
            domain.currencies.map {
                currencyModelMapper.mapToPresentation(it)
            },
            domain.demonym,
            domain.flag,
            domain.languages.map {
                languageModelPresentationMapper.mapToPresentation(it)
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

    override fun mapToDomain(presentation: CountryPresentationModel): Country {
        return Country(
            presentation.alpha2Code,
            presentation.alpha3Code,
            presentation.area,
            presentation.callingCodes,
            presentation.capital,
            presentation.cioc,
            presentation.currencies.map {
                currencyModelMapper.mapToDomain(it)
            },
            presentation.demonym,
            presentation.flag,
            presentation.languages.map {
                languageModelPresentationMapper.mapToDomain(it)
            },
            presentation.latlng,
            presentation.name,
            presentation.nativeName,
            presentation.numericCode,
            presentation.population,
            presentation.region,
            presentation.subregion
        )
    }


}