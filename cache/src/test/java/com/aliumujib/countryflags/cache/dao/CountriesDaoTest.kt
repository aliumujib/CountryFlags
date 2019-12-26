package com.aliumujib.countryflags.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.aliumujib.countryflags.cache.data.CacheDataFactory
import com.aliumujib.countryflags.cache.room.CountriesDB
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CountriesDaoTest {

    @Rule
    @JvmField var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CountriesDB

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            CountriesDB::class.java)
            .allowMainThreadQueries().build()
    }

    @Test
    fun `check that calling getAllCountries() on dao returns data`() {
        val countryCacheModel = CacheDataFactory.makeCountryCacheModelList(10)
        database.countriesDao().save(countryCacheModel)

        val testObserver = database.countriesDao().getAllCountries().test()
        testObserver.assertValue(countryCacheModel)
    }


    @After
    fun tearDown() {
        database.close()
    }
}
