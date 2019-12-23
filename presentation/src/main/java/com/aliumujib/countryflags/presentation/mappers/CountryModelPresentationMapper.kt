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
            domain.population
        )
    }

    override fun mapToDomain(view: CountryPresentationModel): Country {
        return Country(
            view.alpha2Code,
            view.alpha3Code,
            view.area,
            view.callingCodes,
            view.capital,
            view.cioc,
            view.currencies.map {
                currencyModelMapper.mapToDomain(it)
            },
            view.demonym,
            view.flag,
            view.languages.map {
                languageModelPresentationMapper.mapToDomain(it)
            },
            view.latlng,
            view.name,
            view.nativeName,
            view.numericCode,
            view.population
        )
    }


}