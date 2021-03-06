package com.aliumujib.countryflags.ui.inject.module.data

import android.content.Context
import androidx.room.Room
import com.aliumujib.countryflags.cache.implementation.CountriesCacheImpl
import com.aliumujib.countryflags.cache.room.CountriesDao
import com.aliumujib.countryflags.cache.room.CountriesDB
import com.aliumujib.countryflags.data.contracts.ICountriesCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
 class CacheModule {

    @Provides
    @Singleton
    fun providesCountriesCache(repo: CountriesCacheImpl): ICountriesCache {
        return repo
    }

    @Provides
    @Singleton
    fun providesCountriesDB(context: Context): CountriesDB =
        Room.databaseBuilder(
            context.applicationContext,
            CountriesDB::class.java,
            "CountriesDatabase"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesCountriesDao(database: CountriesDB): CountriesDao = database.countriesDao()
}