package com.aliumujib.countryflags.data

import com.aliumujib.countryflags.data.model.CountryEntity
import com.aliumujib.countryflags.data.model.CurrencyEntity
import com.aliumujib.countryflags.data.model.LanguageEntity
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.models.Currency
import com.aliumujib.countryflags.domain.models.Language
import konveyor.base.randomBuild

object DummyDataFactory {

    fun makeRandomCurrencyEntity(): CurrencyEntity {
        return randomBuild()
    }

    fun makeRandomLanguageEntity(): LanguageEntity {
        return randomBuild()
    }

    fun makeRandomCountryEntity(): CountryEntity {
        return randomBuild()
    }

    fun makeRandomLanguage(id: String = randomBuild()): Language {
        return Language(
            randomBuild(),
            randomBuild(),
            randomBuild(),
            id
        )
    }


    fun makeRandomCurrency(id: String = randomBuild()): Currency {
        return Currency(
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeRandomCountry(): Country {
        return randomBuild()
    }


    fun makeCountryEntityList(count: Int): List<CountryEntity> {
        val articles = mutableListOf<CountryEntity>()
        repeat(count) {
            articles.add(makeRandomCountryEntity())
        }
        return articles
    }

}