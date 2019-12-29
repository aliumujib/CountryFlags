package com.aliumujib.countryflags.domain.usecases.countries

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.domain.test.CountriesDataFactory
import com.aliumujib.countryflags.domain.util.TestPostExecutionThread
import com.nhaarman.mockito_kotlin.atMost
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import konveyor.base.randomBuild
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchCountriesTest {

    private lateinit var searchCountries: SearchCountries
    @Mock
    lateinit var countriesRepository: ICountriesRepository

    private val postExecutionThread: PostExecutionThread = TestPostExecutionThread()

    @Mock
    lateinit var threadExecutor: ThreadExecutor


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        searchCountries =
            SearchCountries(countriesRepository, threadExecutor, postExecutionThread)
    }


    @Test(expected = IllegalArgumentException::class)
    fun `confirm that calling searchCountries without params returns an error`() {
        val country = CountriesDataFactory.makeCountry()
        val countries = CountriesDataFactory.makeCountryList(3)
        stubSearchCountries(country.name, countries)
        val testObserver =
            searchCountries.buildUseCaseMaybe().test()
        testObserver.assertResult(countries)
    }

    @Test
    fun `confirm that calling searchCountries returns data`() {
        val country = CountriesDataFactory.makeCountry()
        val countries = CountriesDataFactory.makeCountryList(3)
        stubSearchCountries(country.name, countries)
        val testObserver =
            searchCountries.buildUseCaseMaybe(SearchCountries.Params(country.name)).test()
        testObserver.assertResult(countries)
    }

    @Test
    fun `confirm that calling searchCountries calls CountriesRepository searchCountries() method only once`() {
        val country = CountriesDataFactory.makeCountry()
        val countries = CountriesDataFactory.makeCountryList(3)
        stubSearchCountries(country.name, countries)
        searchCountries.buildUseCaseMaybe(SearchCountries.Params(country.name)).test()
        verify(countriesRepository, atMost(1)).searchCountries(country.name)
    }


    @Test
    fun `confirm that calling searchCountries completes`() {
        val query = randomBuild<String>()
        stubSearchCountries(query, CountriesDataFactory.makeCountryList(10))
        val testObserver =
            searchCountries.buildUseCaseMaybe(SearchCountries.Params(query)).test()
        testObserver.assertComplete()
    }

    private fun stubSearchCountries(query: String, countries: List<Country>) {
        whenever(countriesRepository.searchCountries(query))
            .thenReturn(Maybe.just(countries))
    }

}