package com.aliumujib.countryflags.domain.usecases.countries

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.domain.test.CountriesDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import konveyor.base.randomBuild
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RefreshCountriesTest {

    private lateinit var refreshCountries: RefreshCountries
    @Mock
    lateinit var countriesRepository: ICountriesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        refreshCountries = RefreshCountries(countriesRepository, postExecutionThread)
    }

    @Test
    fun `confirm that calling refreshCountriesTest completes`() {
        stubRefreshCountries()
        val testObserver = refreshCountries.buildUseCaseCompletable().test()
        testObserver.assertComplete()
    }

    private fun stubRefreshCountries() {
        whenever(countriesRepository.refreshCountries())
            .thenReturn(Completable.complete())
    }

}