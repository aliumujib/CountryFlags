package com.aliumujib.countryflags.ui.inject.module.ui.allcountriesfragment

import com.aliumujib.countryflags.ui.allcountries.AllCountriesFragment
import com.aliumujib.countryflags.ui.countrydetail.CountryDetailsFragment
import com.aliumujib.countryflags.ui.inject.module.ui.characterdetailsfragment.CountryDetailsFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllCountriesFragmentProvider {

    @ContributesAndroidInjector(modules = [AllCountriesFragmentModule::class])
    abstract fun contributesAllCountriesFragment(): AllCountriesFragment

    @ContributesAndroidInjector(modules = [CountryDetailsFragmentModule::class])
    abstract fun contributesCountryDetailsFragment(): CountryDetailsFragment
}