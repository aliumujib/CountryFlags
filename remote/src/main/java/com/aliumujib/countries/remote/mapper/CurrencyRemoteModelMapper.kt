package com.aliumujib.countries.remote.mapper

import com.aliumujib.countries.remote.countries.CurrencyRemoteModel
import com.aliumujib.countryflags.data.model.CurrencyEntity
import javax.inject.Inject

class CurrencyRemoteModelMapper @Inject constructor() :
    RemoteModelMapper<CurrencyRemoteModel, CurrencyEntity> {

    override fun mapFromModel(model: CurrencyRemoteModel): CurrencyEntity {
        return CurrencyEntity(
            safeString(model.code),
            safeString(model.name),
            safeString(model.symbol)
        )
    }

}