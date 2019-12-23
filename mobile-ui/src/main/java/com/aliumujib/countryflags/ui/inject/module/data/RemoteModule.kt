package com.aliumujib.countryflags.ui.inject.module.data

import com.aliumujib.countryflags.BuildConfig

import com.aliumujib.countries.remote.api.CountriesAPI
import com.aliumujib.countries.remote.api.CountriesServiceFactory
import com.aliumujib.countries.remote.implementation.CountriesRemoteImpl
import com.aliumujib.countryflags.data.contracts.ICountriesRemote

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideCountriesAPI(): CountriesAPI {
            return CountriesServiceFactory.makeCountriesService(BuildConfig.DEBUG, BuildConfig.API_URL)
        }
    }

    @Binds
    abstract fun bindsCountriesRemote(remote: CountriesRemoteImpl): ICountriesRemote

}