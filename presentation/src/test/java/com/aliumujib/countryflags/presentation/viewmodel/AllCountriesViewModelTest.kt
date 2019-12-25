package com.aliumujib.countryflags.presentation.viewmodel

import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.usecases.countries.FetchAllCountries
import com.aliumujib.countryflags.domain.usecases.countries.SearchCountries
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesIntent
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesProcessorHolder
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesViewModel
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesViewState
import com.aliumujib.countryflags.presentation.data.PresentationDataFactory
import com.aliumujib.countryflags.presentation.mappers.CountryModelPresentationMapper
import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class AllCountriesViewModelTest {

    @Mock
    lateinit var fetchAllCountries: FetchAllCountries
    @Mock
    lateinit var searchCountries: SearchCountries
    @Mock
    lateinit var countryModelPresentationMapper: CountryModelPresentationMapper


    private lateinit var allCountriesViewModel: AllCountriesViewModel
    private lateinit var allCountriesProcessorHolder: AllCountriesProcessorHolder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        allCountriesProcessorHolder = AllCountriesProcessorHolder(
            fetchAllCountries,
            searchCountries,
            countryModelPresentationMapper
        )
        allCountriesViewModel = AllCountriesViewModel(allCountriesProcessorHolder)
    }

    @Test
    fun `check that LoadAllCountriesIntent returns success`() {
        val list = PresentationDataFactory.makeCountryList(2)
        val viewList = PresentationDataFactory.makeCountryPresentationList(2)
        stubCountryMapperMapToView(viewList[0], list[0])
        stubCountryMapperMapToView(viewList[1], list[1])
        stubGetCountries(Maybe.just(list))

        val testObserver = allCountriesViewModel.states().test()

        allCountriesViewModel.processIntents(Observable.just(AllCountriesIntent.LoadAllCountriesIntent(true)))

        testObserver.assertValueAt(2) { it is AllCountriesViewState.Success }
    }


    @Test
    fun `check that LoadAllCountriesIntent returns correct data`() {
        val list = PresentationDataFactory.makeCountryList(2)
        val viewList = PresentationDataFactory.makeCountryPresentationList(2)
        stubCountryMapperMapToView(viewList[0], list[0])
        stubCountryMapperMapToView(viewList[1], list[1])
        stubGetCountries(Maybe.just(list))

        val testObserver = allCountriesViewModel.states().test()

        allCountriesViewModel.processIntents(Observable.just(AllCountriesIntent.LoadAllCountriesIntent(true)))

        testObserver.assertValueAt(2) { it.data == viewList }
    }

    @Test
    fun `check that LoadAllCountriesIntent returns an error state when there's an error in the RxChain`() {
        stubGetCountries(Maybe.error(RuntimeException()))

        val testObserver = allCountriesViewModel.states().test()

        allCountriesViewModel.processIntents(Observable.just(AllCountriesIntent.LoadAllCountriesIntent(true)))

        testObserver.assertValueAt(2) { it is AllCountriesViewState.Error }
    }

    private fun stubCountryMapperMapToView(countryPresentationModel: CountryPresentationModel,
                                           country: Country) {
        whenever(countryModelPresentationMapper.mapToPresentation(country))
            .thenReturn(countryPresentationModel)
    }

    private fun stubGetCountries(maybe: Maybe<List<Country>>) {
        whenever(fetchAllCountries.execute(anyOrNull()))
            .thenReturn(maybe)
    }

}