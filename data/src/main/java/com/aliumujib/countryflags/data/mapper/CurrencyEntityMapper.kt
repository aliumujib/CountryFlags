package com.aliumujib.countryflags.data.mapper

import com.aliumujib.countryflags.data.mapper.base.EntityMapper
import com.aliumujib.countryflags.data.model.CurrencyEntity
import com.aliumujib.countryflags.domain.models.Currency
import javax.inject.Inject

class CurrencyEntityMapper @Inject constructor() : EntityMapper<CurrencyEntity, Currency>() {

    override fun mapFromEntity(entity: CurrencyEntity): Currency {
        return Currency(
            entity.code,
            entity.name,
            entity.symbol
        )
    }

    override fun mapToEntity(domain: Currency): CurrencyEntity {
        return CurrencyEntity(
            domain.code,
            domain.name,
            domain.symbol
        )
    }

}