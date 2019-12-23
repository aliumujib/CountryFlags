package com.aliumujib.countryflags.mappers

import com.aliumujib.countryflags.models.CurrencyModel
import com.aliumujib.countryflags.presentation.models.CurrencyPresentationModel
import javax.inject.Inject

class CurrencyModelMapper @Inject constructor() : ModelMapper<CurrencyPresentationModel, CurrencyModel> {

    override fun mapToView(presentation: CurrencyPresentationModel): CurrencyModel {
        return CurrencyModel(
            presentation.code,
            presentation.name,
            presentation.symbol
        )
    }

    override fun mapToPresentation(view: CurrencyModel): CurrencyPresentationModel {
        return CurrencyPresentationModel(
            view.code,
            view.name,
            view.symbol
        )
    }


}