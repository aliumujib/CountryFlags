package com.aliumujib.countries.remote.mapper

import com.aliumujib.countries.remote.countries.CountryRemoteModel
import com.aliumujib.countryflags.data.model.CountryEntity
import javax.inject.Inject

class CountryRemoteModelMapper @Inject constructor(
    private val currencyRemoteModelMapper: CurrencyRemoteModelMapper,
    private val languageRemoteModelMapper: LanguageRemoteModelMapper
) :
    RemoteModelMapper<CountryRemoteModel, CountryEntity> {


    override fun mapFromModel(model: CountryRemoteModel): CountryEntity {
        return CountryEntity(
            model.alpha2Code,
            model.alpha3Code,
            safeDouble(model.area),
            model.callingCodes,
            model.capital,
            safeString(model.cioc),
            model.currencies.map {
                currencyRemoteModelMapper.mapFromModel(it)
            },
            safeString(model.demonym),
            model.flag,
            model.languages.map {
                languageRemoteModelMapper.mapFromModel(it)
            },
            model.latlng,
            model.name,
            safeString(model.nativeName),
            safeString(model.numericCode),
            safeInt(model.population)
        )
    }


}