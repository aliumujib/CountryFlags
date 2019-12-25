package com.aliumujib.countryflags.cache.data

import com.aliumujib.countryflags.cache.models.CountryCacheModel
import com.aliumujib.countryflags.cache.models.CurrencyCacheModel
import com.aliumujib.countryflags.cache.models.LanguageCacheModel
import com.aliumujib.countryflags.data.model.CountryEntity
import com.aliumujib.countryflags.data.model.CurrencyEntity
import com.aliumujib.countryflags.data.model.LanguageEntity
import konveyor.base.randomBuild

object CacheDataFactory {


    fun makeRandomLanguageCacheModel(id: String = randomBuild()): LanguageCacheModel {
        return LanguageCacheModel(
            randomBuild(),
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeRandomLanguageEntity(id: String = randomBuild()): LanguageEntity {
        return LanguageEntity(
            randomBuild(),
            randomBuild(),
            randomBuild(),
            id
        )
    }


    fun makeRandomCurrencyEntity(id: String = randomBuild()): CurrencyEntity {
        return CurrencyEntity(
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeRandomCurrencyCacheModel(id: String = randomBuild()): CurrencyCacheModel {
        return CurrencyCacheModel(
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeRandomCountry(): CountryEntity {
        return randomBuild()
    }

    fun makeCountryCacheModel(): CountryCacheModel {
        return randomBuild()
    }

    fun makeCountryEntityList(count: Int): List<CountryEntity> {
        val articles = mutableListOf<CountryEntity>()
        repeat(count) {
            articles.add(makeRandomCountry())
        }
        return articles
    }

}