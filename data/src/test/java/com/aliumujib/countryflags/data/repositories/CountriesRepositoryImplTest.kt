package com.aliumujib.countryflags.data.repositories

import com.aliumujib.countryflags.data.DummyDataFactory
import com.aliumujib.countryflags.data.contracts.ICountriesCache
import com.aliumujib.countryflags.data.contracts.ICountriesRemote
import com.aliumujib.countryflags.data.mapper.CountryEntityMapper
import com.aliumujib.countryflags.data.mapper.CurrencyEntityMapper
import com.aliumujib.countryflags.data.mapper.LanguageEntityMapper
import com.aliumujib.countryflags.data.model.CountryEntity
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.Single
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
    fun `check that calling refreshCountries on repository calls remote implementation`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        stubGetCountriesRemoteResponse(data)
        countriesRepositoryImpl.refreshCountries().test()
        verify(iCountriesRemote).fetchAllCountries()
    }


    @Test
    fun `check that calling refreshCountries on repository completes`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        stubGetCountriesRemoteResponse(data)
        val testObserver = countriesRepositoryImpl.refreshCountries().test()
        testObserver.assertComplete()
    }

    @Test
    fun `check that calling refreshCountries on repository saves data when returned`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        stubGetCountriesRemoteResponse(data)
        countriesRepositoryImpl.refreshCountries().test()
        verify(iCountriesCache).saveCountries(data)
    }


    @Test
    fun `check that calling fetchCountries on repository returns correct data`() {
        val data = DummyDataFactory.makeCountryEntityList(10)
        val mappedData = countryEntityMapper.mapFromEntityList(data)
        stubGetCountriesCacheResponse(data)
        val testObserver = countriesRepositoryImpl.fetchCountries().test()
        testObserver.assertValue(mappedData)
    }



    private fun stubGetCountriesCacheResponse(
        countries: List<CountryEntity>
    ) {
        whenever(iCountriesCache.fetchCountries())
            .thenReturn(Flowable.just(countries))
    }

    private fun stubGetCountriesRemoteResponse(
        countries: List<CountryEntity>
    ) {
        whenever(iCountriesRemote.fetchAllCountries())
            .thenReturn(Single.just(countries))
    }

}