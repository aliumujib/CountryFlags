package com.aliumujib.countryflags.injection.data

import com.aliumujib.countryflags.data.executor.JobExecutor
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides


@Module
 class TestDataModule {

    @Provides
     fun providesCountriesRepository(): ICountriesRepository{
        return mock()
    }

    @Provides
    fun providesThreadExecutor(): ThreadExecutor{
        return JobExecutor()
    }

}