package com.aliumujib.countries.remote

import com.aliumujib.countries.remote.api.CountriesAPI
import com.aliumujib.countries.remote.countries.CountryRemoteModel
import com.aliumujib.countries.remote.data.RemoteDataFactory
import com.aliumujib.countries.remote.implementation.CountriesRemoteImpl
import com.aliumujib.countries.remote.mapper.CountryRemoteModelMapper
import com.aliumujib.countries.remote.mapper.CurrencyRemoteModelMapper
import com.aliumujib.countries.remote.mapper.LanguageRemoteModelMapper
import com.aliumujib.countryflags.data.contracts.ICountriesRemote
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import konveyor.base.randomBuild
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.net.SocketTimeoutException

@RunWith(JUnit4::class)
class CountriesRemoteImplTest {

    private lateinit var iCountriesRemote: ICountriesRemote

    @Mock
    lateinit var countriesAPI: CountriesAPI

    private val currencyMapper = CurrencyRemoteModelMapper()
    private val languageMapper = LanguageRemoteModelMapper()
    private val countryMapper =
        CountryRemoteModelMapper(currencyMapper, languageMapper)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        iCountriesRemote =
            CountriesRemoteImpl(
                countriesAPI,
                countryMapper
            )

    }


    @Test
    fun `check that calling fetchCountries on remote returns data`() {
        val data = RemoteDataFactory.makeCountryRemoteModelList(10)
        stubGetCountriesRemoteResponse(data)
        val mappedData = data.map {
            countryMapper.mapFromModel(it)
        }
        val testObserver = iCountriesRemote.fetchAllCountries().test()
        testObserver.assertValue(mappedData)
    }

    @Test
    fun `check that calling fetchCountries on remote completes`() {
        val data = RemoteDataFactory.makeCountryRemoteModelList(10)
        stubGetCountriesRemoteResponse(data)
        val testObserver = iCountriesRemote.fetchAllCountries().test()
        testObserver.assertComplete()
    }


    @Test
    fun `check that calling searchCountries on remote returns data`() {
        val data = RemoteDataFactory.makeCountryRemoteModelList(10)
        val query = randomBuild<String>()
        stubSearchCountriesRemoteResponse(query, data)
        val mappedData = data.map {
            countryMapper.mapFromModel(it)
        }
        val testObserver = iCountriesRemote.searchCountries(query).test()
        testObserver.assertValue(mappedData)
    }

    @Test
    fun `check that calling searchCountries on remote completes`() {
        val query = randomBuild<String>()
        val data = RemoteDataFactory.makeCountryRemoteModelList(10)
        stubSearchCountriesRemoteResponse(query, data)
        val testObserver = iCountriesRemote.searchCountries(query).test()
        testObserver.assertComplete()
    }

    @Test
    fun `check that calling searchCountries on remote errors when there's an error from the API call`() {
        val query = randomBuild<String>()
        val throwable = SocketTimeoutException()
        stubSearchCountriesRemoteError(query, throwable)
        val testObserver = iCountriesRemote.searchCountries(query).test()
        testObserver.assertError(throwable)
    }

    @Test
    fun `check that calling fetchAllCountries on remote errors when there's an error from the API call`() {
        val throwable = SocketTimeoutException()
        stubGetCountriesRemoteError(throwable)
        val testObserver = iCountriesRemote.fetchAllCountries().test()
        testObserver.assertError(throwable)
    }

    private fun stubSearchCountriesRemoteError(
        query: String,
        error: Throwable
    ) {
        whenever(countriesAPI.searchCountriesByName(query))
            .thenReturn(Maybe.error(error))
    }

    private fun stubGetCountriesRemoteError(
        error: Throwable
    ) {
        whenever(countriesAPI.fetchAllCountries())
            .thenReturn(Maybe.error(error))
    }

    private fun stubSearchCountriesRemoteResponse(
        query: String,
        countries: List<CountryRemoteModel>
    ) {
        whenever(countriesAPI.searchCountriesByName(query))
            .thenReturn(Maybe.just(countries))
    }

    private fun stubGetCountriesRemoteResponse(
        countries: List<CountryRemoteModel>
    ) {
        whenever(countriesAPI.fetchAllCountries())
            .thenReturn(Maybe.just(countries))
    }

}