package com.aliumujib.countryflags.domain.usecases.countries

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.domain.test.CountriesDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.internal.operators.flowable.FlowableJoin
import konveyor.base.randomBuild
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

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        fetchAllCountries = FetchAllCountries(countriesRepository, postExecutionThread)
    }

    @Test
    fun `confirm that calling fetchAllCountries completes`() {
        stubFetchAllCountries(CountriesDataFactory.makeCountryList(10))
        val testObserver = fetchAllCountries.buildUseCaseFlowable().test()
        testObserver.assertComplete()
    }

    @Test
    fun `confirm that calling fetchAllCountries returns data`() {
        val list = CountriesDataFactory.makeCountryList(10)
        stubFetchAllCountries(list)
        val testObserver = fetchAllCountries.buildUseCaseFlowable().test()
        testObserver.assertValue(list)
    }

    private fun stubFetchAllCountries(data: List<Country>) {
        whenever(countriesRepository.fetchCountries())
            .thenReturn(Flowable.just(data))
    }

}