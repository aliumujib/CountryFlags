package com.aliumujib.countryflags.ui.inject.module.ui.allcountries

import com.aliumujib.countryflags.navigator.Navigator
import com.aliumujib.countryflags.navigator.NavigatorImpl
import com.aliumujib.countryflags.ui.AllCountriesActivity
import dagger.Module
import dagger.Provides

@Module
class AllCountriesActivityModule(
) {

    @Provides
    fun providesNavigator(allCountriesActivity: AllCountriesActivity): Navigator {
        return NavigatorImpl(
            allCountriesActivity, allCountriesActivity.supportFragmentManager
        )
    }

}