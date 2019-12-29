package com.aliumujib.countryflags.cache.implementation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.aliumujib.countryflags.cache.data.CacheDataFactory
import com.aliumujib.countryflags.cache.mapper.CountryCacheModelMapper
import com.aliumujib.countryflags.cache.mapper.CurrencyCacheModelMapper
import com.aliumujib.countryflags.cache.mapper.LanguageCacheModelMapper
import com.aliumujib.countryflags.cache.room.CountriesDB
import com.aliumujib.countryflags.data.contracts.ICountriesCache
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CountriesCacheImplTest {

    private lateinit var countriesCache: ICountriesCache

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        CountriesDB::class.java
    )
        .allowMainThreadQueries().build()

    private val currencyMapper = CurrencyCacheModelMapper()
    private val languageMapper = LanguageCacheModelMapper()
    private val countryMapper =
        CountryCacheModelMapper(currencyMapper, languageMapper)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        countriesCache =
            CountriesCacheImpl(
                database.countriesDao(),
                countryMapper
            )

    }


    @Test
    fun `check that calling fetchCountries on cache returns data`() {
        val data = CacheDataFactory.makeCountryEntityList(10)
        countriesCache.saveCountries(data)
        val testObserver = countriesCache.fetchAllCountries().test()
        testObserver.assertValue(data)
    }

    @Test
    fun `check that calling fetchCountries on cache completes`() {
        val testObserver = countriesCache.fetchAllCountries().test()
        testObserver.assertComplete()
    }


}