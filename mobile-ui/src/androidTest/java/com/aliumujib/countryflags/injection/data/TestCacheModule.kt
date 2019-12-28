package com.aliumujib.countryflags.injection.data

import android.content.Context
import androidx.room.Room
import com.aliumujib.countryflags.cache.room.CountriesDB
import com.aliumujib.countryflags.cache.room.CountriesDao
import com.aliumujib.countryflags.data.contracts.ICountriesCache
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
 class TestCacheModule {

    @Provides
    @Singleton
    fun providesCountriesCache(): ICountriesCache {
        return mock()
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