package com.aliumujib.countryflags.navigator

import android.os.Bundle
import com.aliumujib.countryflags.models.CountryModel

interface Navigator {
    fun goToDetailScreen(countryModel: CountryModel)
    fun goBack()
    fun start(savedInstanceState: Bundle?)
    fun showCountryList()
    fun setupActionBar()
}