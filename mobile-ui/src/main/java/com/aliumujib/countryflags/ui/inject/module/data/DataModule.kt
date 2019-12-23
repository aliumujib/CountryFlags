package com.aliumujib.countryflags.ui.inject.module.data

import com.aliumujib.countryflags.data.executor.JobExecutor
import com.aliumujib.countryflags.data.repositories.CountriesRepositoryImpl
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import dagger.Binds
import dagger.Module


@Module
abstract class DataModule {

    @Binds
    abstract fun bindsCountriesRepository(repo: CountriesRepositoryImpl): ICountriesRepository

    @Binds
    abstract fun bindsThreadExecutor(executor: JobExecutor): ThreadExecutor

}