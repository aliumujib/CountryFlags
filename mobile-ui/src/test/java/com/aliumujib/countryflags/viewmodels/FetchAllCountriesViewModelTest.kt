package com.aliumujib.countryflags.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aliumujib.countryflags.PresentationDataFactory
import com.aliumujib.countryflags.domain.usecases.countries.FetchAllCountries
import com.aliumujib.countryflags.domain.models.DetailedCharacter
import com.aliumujib.countryflags.presentation.mappers.CountryModelPresentationMapper
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import java.lang.RuntimeException

@RunWith(JUnit4::class)
class FetchAllCountriesViewModelTest {

    @get:Rule
    val instantExecuterRule = InstantTaskExecutorRule()

    var fetchCharacterDetails = mock<FetchAllCountries>()

    var detailmodelMapper = mock<CountryModelPresentationMapper>()

    var characterModelMapper = StarwarsCharacterModelPresentationMapper()



    var projectsViewModel: FetchCharacterDetailsViewModel =
        FetchCharacterDetailsViewModel(fetchCharacterDetails, characterModelMapper, detailmodelMapper)

    @Captor
    val captor = argumentCaptor<DisposableObserver<DetailedCharacter>>()


    @Test
    fun `check that calling fetchCharacterDetails in viewModel executes FetchCharacterDetails`() {
        val data = PresentationDataFactory.makeCharacterModel()
        projectsViewModel.fetchCharacterDetails(data)
        verify(fetchCharacterDetails, times(1)).execute(any(), any())
    }


    @Test
    fun `check that calling getCharacterDetailsLiveData on viewModel returns success when data is returned`() {
        val characterData = PresentationDataFactory.makeCharacterModel()
        val data = PresentationDataFactory.makeDetailedCharacter()
        val detailsData = PresentationDataFactory.makeDetailedCharacterModel()

        stubCharacterDetails(data, detailsData)

        projectsViewModel.fetchCharacterDetails(characterData)
        verify(fetchCharacterDetails).execute(captor.capture(), eq(FetchAllCountries.Params(characterModelMapper.mapToDomain(characterData))))

        captor.firstValue.onNext(data)

        assertEquals(ResourceState.SUCCESS, projectsViewModel.getCharacterDetailsLiveData().value?.status)
    }


    @Test
    fun `check that getCharacterDetailsLiveData in viewModel returns correct mapped data`() {
        val characterData = PresentationDataFactory.makeCharacterModel()
        val data = PresentationDataFactory.makeDetailedCharacter()
        val detailsData = PresentationDataFactory.makeDetailedCharacterModel()

        stubCharacterDetails(data, detailsData)

        projectsViewModel.fetchCharacterDetails(characterData)
        verify(fetchCharacterDetails).execute(captor.capture(), eq(FetchAllCountries.Params(characterModelMapper.mapToDomain(characterData))))

        captor.firstValue.onNext(data)

        assertEquals(detailsData, projectsViewModel.getCharacterDetailsLiveData().value?.data)
    }

    @Test
    fun `check that getCharacterDetailsLiveData in viewModel returns error when an error occurs`() {
        val characterData = PresentationDataFactory.makeCharacterModel()
        projectsViewModel.fetchCharacterDetails(characterData)

        verify(fetchCharacterDetails).execute(captor.capture(), eq(FetchAllCountries.Params(characterModelMapper.mapToDomain(characterData))))

        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, projectsViewModel.getCharacterDetailsLiveData().value?.status)
    }

    fun stubCharacterDetails(character: DetailedCharacter, characterModel: DetailedCharacterModel) {
        whenever(detailmodelMapper.mapToPresentation(character)).thenReturn(characterModel)
    }


}
