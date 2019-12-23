package com.aliumujib.countryflags.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aliumujib.countryflags.PresentationDataFactory
import com.aliumujib.countryflags.domain.usecases.countries.CheckIfCountryIsBookmarked
import com.aliumujib.countryflags.domain.models.StarWarsCharacter
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
class CheckIfCountryIsBookmarkedViewModelTest {

    @get:Rule
    val instantExecuterRule = InstantTaskExecutorRule()

    var searchCharacters = mock<CheckIfCountryIsBookmarked>()

    var modelMapper = mock<StarwarsCharacterModelPresentationMapper>()

    var projectsViewModel: SearchCharactersViewModel = SearchCharactersViewModel(searchCharacters, modelMapper)

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<StarWarsCharacter>>>()


    @Test
    fun `check that calling search in viewModel executes SearchCharacters when character size is more than 2`() {
        projectsViewModel.searchCharacters("luke")
        verify(searchCharacters, times(1)).execute(any(), any())
    }

    @Test
    fun `check that calling search in viewModel doesn't executes SearchCharacters when character size is less than 3`() {
        projectsViewModel.searchCharacters("lu")
        verifyZeroInteractions(searchCharacters)
    }


    @Test
    fun `check that calling search in viewModel returns success when data is returned`() {
        val listOfCharacters = PresentationDataFactory.makeCharacterList(2)
        val listOfMappedCharacters = PresentationDataFactory.makeCharacterModelList(2)

        listOfCharacters.forEachIndexed { index, starWarsCharacter ->
            stubCharacter(starWarsCharacter, listOfMappedCharacters[index])
        }

        projectsViewModel.searchCharacters("luke")
        verify(searchCharacters).execute(captor.capture(), eq(CheckIfCountryIsBookmarked.Params("luke")))

        captor.firstValue.onNext(listOfCharacters)

        assertEquals(ResourceState.SUCCESS, projectsViewModel.getCharactersLiveData().value?.status)
    }


    @Test
    fun `check that calling search in viewModel returns correct mapped data`() {
        val listOfCharacters = PresentationDataFactory.makeCharacterList(2)
        val listOfMappedCharacters = PresentationDataFactory.makeCharacterModelList(2)

        listOfCharacters.forEachIndexed { index, starWarsCharacter ->
            stubCharacter(starWarsCharacter, listOfMappedCharacters[index])
        }

        projectsViewModel.searchCharacters("luke")
        verify(searchCharacters).execute(captor.capture(), eq(CheckIfCountryIsBookmarked.Params("luke")))

        captor.firstValue.onNext(listOfCharacters)

        assertEquals(listOfMappedCharacters, projectsViewModel.getCharactersLiveData().value?.data)
    }

    @Test
    fun `check that calling search in viewModel returns error when an error occurs`() {

        projectsViewModel.searchCharacters("luke")
        verify(searchCharacters).execute(captor.capture(), eq(CheckIfCountryIsBookmarked.Params("luke")))

        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, projectsViewModel.getCharactersLiveData().value?.status)
    }

    fun stubCharacter(character: StarWarsCharacter, characterModel: StarWarsCharacterModel){
        whenever(modelMapper.mapToView(character)).thenReturn(characterModel)
    }


}
