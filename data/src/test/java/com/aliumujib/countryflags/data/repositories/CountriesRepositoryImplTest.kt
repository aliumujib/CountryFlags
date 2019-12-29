package com.aliumujib.countryflags.data.repositories

import com.aliumujib.countryflags.data.DummyDataFactory
import com.aliumujib.countryflags.data.contracts.ICountriesCache
import com.aliumujib.countryflags.data.contracts.ICountriesRemote
import com.aliumujib.countryflags.data.mapper.CountryEntityMapper
import com.aliumujib.countryflags.data.mapper.CurrencyEntityMapper
import com.aliumujib.countryflags.data.mapper.LanguageEntityMapper
import com.aliumujib.countryflags.data.model.CountryEntity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import konveyor.base.randomBuild
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CountriesRepositoryImplTest {

    private lateinit var countriesRepositoryImpl: CountriesRepositoryImpl

    @Mock
    lateinit var iCountriesRemote: ICountriesRemote

    @Mock
    lateinit var iCountriesCache: ICountriesCache

    private val currencyEntityMapper = CurrencyEntityMapper()
    private val languageEntityMapper = LanguageEntityMapper()


    private val countryEntityMapper =
        CountryEntityMapper(currencyEntityMapper, languageEntityMapper)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        countriesRepositoryImpl =
            CountriesRepositoryImpl(
                iCountriesRemote,
                countryEntityMapper,
                iCountriesCache
            )

    }


    @Test
    fun `check that calling fetchCountries on repository with isConnected as true calls remote implementation`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        stubGetCountriesRemoteResponse(data)
        countriesRepositoryImpl.fetchCountries(true).test()
        verify(iCountriesRemote).fetchAllCountries()
    }

    @Test
    fun `check that calling fetchCountries on repository with isConnected as true doesn't call cache implementation`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        stubGetCountriesRemoteResponse(data)
        countriesRepositoryImpl.fetchCountries(true).test()
        verify(iCountriesCache, never()).fetchAllCountries()
    }

    @Test
    fun `check that calling fetchCountries on repository with isConnected as false calls cache implementation`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        stubGetCountriesCacheResponse(data)
        countriesRepositoryImpl.fetchCountries(false).test()
        verify(iCountriesCache).fetchAllCountries()
    }

    @Test
    fun `check that calling fetchCountries on repository with isConnected as false doesn't call remote implementation`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        stubGetCountriesCacheResponse(data)
        countriesRepositoryImpl.fetchCountries(false).test()
        verify(iCountriesRemote, never()).fetchAllCountries()
    }

    @Test
    fun `check that calling searchCountries on repository completes`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        val query = randomBuild<String>()
        stubSearchCountriesRemoteResponse(query, data)
        val testObserver = countriesRepositoryImpl.searchCountries(query).test()
        testObserver.assertComplete()
    }

    @Test
    fun `check that calling fetchAllCountries on repository when isConnected is true saves data when successful`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        stubGetCountriesRemoteResponse(data)
        countriesRepositoryImpl.fetchCountries(true).test()
        verify(iCountriesCache).saveCountries(data)
    }

    @Test
    fun `check that calling fetchAllCountries on repository when isConnected is true never saves data when there's an error in the request`() {
        val error: Throwable = randomBuild()
        stubGetCountriesRemoteError(error)
        countriesRepositoryImpl.fetchCountries(true).test()
        verify(iCountriesCache, never()).saveCountries(any())
    }

    @Test
    fun `check that calling fetchAllCountries on repository returns an error when there's an error in the request`() {
        val error: Throwable = randomBuild()
        stubGetCountriesRemoteError(error)
        val testObserver = countriesRepositoryImpl.fetchCountries(true).test()
        testObserver.assertError(error)
    }

    @Test
    fun `check that calling fetchAllCountries on repository when isConnected is false never saves data `() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        stubGetCountriesCacheResponse(data)
        countriesRepositoryImpl.fetchCountries(false).test()
        verify(iCountriesCache, never()).saveCountries(data)
    }

    @Test
    fun `check that calling fetchCountries on repository returns correct data`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        val mappedData = countryEntityMapper.mapFromEntityList(data)
        stubGetCountriesRemoteResponse(data)
        val testObserver = countriesRepositoryImpl.fetchCountries(true).test()
        testObserver.assertValue(mappedData)
    }

    private fun stubGetCountriesRemoteError(
        error: Throwable
    ) {
        whenever(iCountriesRemote.fetchAllCountries())
            .thenReturn(Maybe.error(error))
    }

    private fun stubGetCountriesCacheResponse(
        countries: List<CountryEntity>
    ) {
        whenever(iCountriesCache.fetchAllCountries())
            .thenReturn(Maybe.just(countries))
    }

    private fun stubGetCountriesRemoteResponse(
        countries: List<CountryEntity>
    ) {
        whenever(iCountriesRemote.fetchAllCountries())
            .thenReturn(Maybe.just(countries))
    }

    private fun stubSearchCountriesRemoteResponse(
        query: String,
        countries: List<CountryEntity>
    ) {
        whenever(iCountriesRemote.searchCountries(query))
            .thenReturn(Maybe.just(countries))
    }

}