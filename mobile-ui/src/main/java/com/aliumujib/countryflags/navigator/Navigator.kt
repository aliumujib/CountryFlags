package com.aliumujib.countryflags.navigator

import com.aliumujib.countryflags.models.CountryModel

interface Navigator {
    fun goToDetailScreen(countryModel: CountryModel)
    fun goBack()
    fun showCountryList()
    fun setupActionBar()
}