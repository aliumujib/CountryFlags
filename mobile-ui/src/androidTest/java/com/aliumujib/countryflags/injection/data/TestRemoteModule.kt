package com.aliumujib.countryflags.injection.data

import com.aliumujib.countries.remote.api.CountriesAPI
import com.aliumujib.countryflags.data.contracts.ICountriesRemote
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

@Module
class TestRemoteModule {

    @Provides
    fun provideCountriesAPI(): CountriesAPI {
        return mock()
    }

    @Provides
    fun providesCountriesRemote(): ICountriesRemote {
        return mock()
    }

}