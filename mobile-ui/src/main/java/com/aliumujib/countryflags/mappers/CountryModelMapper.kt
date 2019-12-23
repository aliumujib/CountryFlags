package com.aliumujib.countryflags.mappers

import com.aliumujib.countryflags.models.CountryModel
import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import javax.inject.Inject

open class CountryModelMapper @Inject constructor(
    var languageModelMapper: LanguageModelMapper,
    var currencyModelMapper: CurrencyModelMapper
) :
    ModelMapper<CountryPresentationModel, CountryModel> {

    override fun mapToView(presentation: CountryPresentationModel): CountryModel {
        return CountryModel(
            presentation.alpha2Code,
            presentation.alpha3Code,
            presentation.area,
            presentation.callingCodes,
            presentation.capital,
            presentation.cioc,
            presentation.currencies.map {
                currencyModelMapper.mapToView(it)
            },
            presentation.demonym,
            presentation.flag,
            presentation.languages.map {
                languageModelMapper.mapToView(it)
            },
            presentation.latlng,
            presentation.name,
            presentation.nativeName,
            presentation.numericCode,
            presentation.population
        )
    }

    override fun mapToPresentation(view: CountryModel): CountryPresentationModel {
        return CountryPresentationModel(
            view.alpha2Code,
            view.alpha3Code,
            view.area,
            view.callingCodes,
            view.capital,
            view.cioc,
            view.currencies.map {
                currencyModelMapper.mapToPresentation(it)
            },
            view.demonym,
            view.flag,
            view.languages.map {
                languageModelMapper.mapToPresentation(it)
            },
            view.latlng,
            view.name,
            view.nativeName,
            view.numericCode,
            view.population
        )
    }


}