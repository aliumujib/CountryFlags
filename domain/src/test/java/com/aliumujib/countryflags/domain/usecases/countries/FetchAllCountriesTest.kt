package com.aliumujib.countryflags.domain.usecases.countries

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.domain.test.CountriesDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.atMost
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchAllCountriesTest {

    private lateinit var fetchAllCountries: FetchAllCountries
    @Mock
    lateinit var countriesRepository: ICountriesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    @Mock
    lateinit var threadExecutor: ThreadExecutor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        fetchAllCountries =
            FetchAllCountries(countriesRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun `confirm that calling fetchAllCountries completes`() {
        stubFetchAllCountries(CountriesDataFactory.makeCountryList(10))
        val testObserver = fetchAllCountries.buildUseCaseMaybe(FetchAllCountries.Params(true)).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `confirm that calling fetchAllCountries without params returns an error`() {
        val country = CountriesDataFactory.makeCountry()
        val countries = CountriesDataFactory.makeCountryList(3)
        stubFetchAllCountries(countries)
        val testObserver =
            fetchAllCountries.buildUseCaseMaybe().test()
        testObserver.assertResult(countries)
    }

    @Test
    fun `confirm that calling fetchAllCountries returns data`() {
        val list = CountriesDataFactory.makeCountryList(10)
        stubFetchAllCountries(list)
        val testObserver = fetchAllCountries.buildUseCaseMaybe(FetchAllCountries.Params(true)).test()
        testObserver.assertValue(list)
    }

    @Test
    fun `confirm that calling fetchAllCountries calls CountriesRepository fetchAllCountries() method only once`() {
        val country = CountriesDataFactory.makeCountry()
        val countries = CountriesDataFactory.makeCountryList(3)
        stubFetchAllCountries(countries)
        fetchAllCountries.buildUseCaseMaybe(FetchAllCountries.Params(true)).test()
        verify(countriesRepository, atMost(1)).fetchCountries(true)
    }

    private fun stubFetchAllCountries(data: List<Country>) {
        whenever(countriesRepository.fetchCountries(any()))
            .thenReturn(Maybe.just(data))
    }

}