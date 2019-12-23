package com.aliumujib.countryflags.ui.inject.module.data

import android.content.Context
import androidx.room.Room
import com.aliumujib.countryflags.cache.implementation.CountriesCacheImpl
import com.aliumujib.countryflags.cache.room.CountriesDao
import com.aliumujib.countryflags.cache.room.CoutriesDB
import com.aliumujib.countryflags.data.contracts.ICountriesCache
import com.aliumujib.countryflags.data.repositories.CountriesRepositoryImpl
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
 class CacheModule {

    @Provides
    @Singleton
    fun bindsCountriesCache(repo: CountriesCacheImpl): ICountriesCache {
        return repo
    }

    @Provides
    @Singleton
    fun providesMTNFDB(context: Context): CoutriesDB =
        Room.databaseBuilder(
            context.applicationContext,
            CoutriesDB::class.java,
            "CountriesDatabase"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesUserDao(database: CoutriesDB): CountriesDao = database.countriesDao()
}