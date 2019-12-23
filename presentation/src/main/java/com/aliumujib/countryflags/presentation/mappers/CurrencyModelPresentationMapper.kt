package com.aliumujib.countryflags.presentation.mappers

import com.aliumujib.countryflags.domain.models.Currency
import com.aliumujib.countryflags.presentation.models.CurrencyPresentationModel
import javax.inject.Inject

class CurrencyModelPresentationMapper @Inject constructor() : PresentationMapper<Currency, CurrencyPresentationModel> {

    override fun mapToPresentation(domain: Currency): CurrencyPresentationModel {
        return CurrencyPresentationModel(
            domain.code,
            domain.name,
            domain.symbol
        )
    }

    override fun mapToDomain(view: CurrencyPresentationModel): Currency {
        return Currency(
            view.code,
            view.name,
            view.symbol
        )
    }

}