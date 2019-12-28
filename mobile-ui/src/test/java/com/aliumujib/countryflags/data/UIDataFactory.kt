package com.aliumujib.countryflags.data

import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.models.Currency
import com.aliumujib.countryflags.domain.models.Language
import com.aliumujib.countryflags.models.CountryModel
import com.aliumujib.countryflags.models.CurrencyModel
import com.aliumujib.countryflags.models.LanguageModel
import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import com.aliumujib.countryflags.presentation.models.CurrencyPresentationModel
import com.aliumujib.countryflags.presentation.models.LanguagePresentationModel
import konveyor.base.randomBuild

object UIDataFactory {

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

    fun makeCurrencyModel(id: String = randomBuild()): CurrencyModel {
        return CurrencyModel(
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeCountryModel(): CountryModel {
        return randomBuild()
    }

    fun makeLanguageModel(): LanguageModel {
        return randomBuild()
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
        val list = mutableListOf<Country>()
        repeat(count) {
            list.add(makeCountry())
        }
        return list
    }

    fun makeCountryModelList(count: Int): List<CountryModel> {
        val list = mutableListOf<CountryModel>()
        repeat(count) {
            list.add(makeCountryModel())
        }
        return list
    }

}