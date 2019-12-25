package com.aliumujib.countryflags.presentation.data

import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.models.Currency
import com.aliumujib.countryflags.domain.models.Language
import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import com.aliumujib.countryflags.presentation.models.CurrencyPresentationModel
import com.aliumujib.countryflags.presentation.models.LanguagePresentationModel
import konveyor.base.randomBuild

object PresentationDataFactory {


    fun makeRandomLanguagePresentationModel(id: String = randomBuild()): LanguagePresentationModel {
        return LanguagePresentationModel(
            randomBuild(),
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeRandomCountryPresentationModel(id: String = randomBuild()): CountryPresentationModel {
        return randomBuild()
    }


    fun makeRandomCurrencyPresentationModel(id: String = randomBuild()): CurrencyPresentationModel {
        return CurrencyPresentationModel(
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeCurrency(id: String = randomBuild()): Currency {
        return Currency(
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeCountry(): Country {
        return randomBuild()
    }

    fun makeLanguage(): Language {
        return randomBuild()
    }


    fun makeCountryPresentationList(count: Int): List<CountryPresentationModel> {
        val mutableList = mutableListOf<CountryPresentationModel>()
        repeat(count) {
            mutableList.add(makeRandomCountryPresentationModel())
        }
        return mutableList
    }

    fun makeCountryList(count: Int): List<Country> {
        val articles = mutableListOf<Country>()
        repeat(count) {
            articles.add(makeCountry())
        }
        return articles
    }

}