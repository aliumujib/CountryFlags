package com.aliumujib.countryflags.cache.mapper

import com.aliumujib.countryflags.cache.models.CurrencyCacheModel
import com.aliumujib.countryflags.data.model.CurrencyEntity
import javax.inject.Inject

class CurrencyCacheModelMapper @Inject constructor() :
    CacheModelMapper<CurrencyCacheModel, CurrencyEntity> {

    override fun mapFromModel(model: CurrencyCacheModel): CurrencyEntity {
        return CurrencyEntity(
            model.code,
            model.name,
            model.symbol
        )
    }

    override fun mapToModel(model: CurrencyEntity): CurrencyCacheModel {
        return CurrencyCacheModel(
            model.code,
            model.name,
            model.symbol
        )
    }

}