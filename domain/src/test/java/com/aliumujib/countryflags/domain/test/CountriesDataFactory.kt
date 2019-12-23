package com.aliumujib.countryflags.domain.test

import com.aliumujib.countryflags.domain.models.Country
import konveyor.base.randomBuild

object CountriesDataFactory {

    fun makeCountry(): Country {
        return randomBuild()
    }

    fun makeCountryList(count: Int): List<Country> {
        val articles = mutableListOf<Country>()
        repeat(count) {
            articles.add(makeCountry())
        }
        return articles
    }

}