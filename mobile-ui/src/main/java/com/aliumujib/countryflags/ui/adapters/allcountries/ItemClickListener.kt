package com.aliumujib.countryflags.ui.adapters.allcountries

import com.aliumujib.countryflags.models.CountryModel

interface OnCountryClickListener {
    fun onCountryClicked(countryModel: CountryModel)
}