package com.aliumujib.countryflags.presentation.mapper


import com.aliumujib.countryflags.domain.models.Language
import com.aliumujib.countryflags.presentation.data.PresentationDataFactory
import com.aliumujib.countryflags.presentation.mappers.LanguageModelPresentationMapper
import com.aliumujib.countryflags.presentation.models.LanguagePresentationModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class LanguageModelPresentationMapperTest {

    private val languageModelPresentationMapper: LanguageModelPresentationMapper = LanguageModelPresentationMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that LanguagePresentationModel mapped to cache Language contains correct info`() {
        val languagePresentationModel: LanguagePresentationModel = PresentationDataFactory.makeRandomLanguagePresentationModel()
        val language = languageModelPresentationMapper.mapToDomain(languagePresentationModel)
        assertEqualData(language, languagePresentationModel)
    }


    @Test
    fun `check that Language mapped to data LanguagePresentationModel contains correct info`() {
        val language: Language = PresentationDataFactory. makeLanguage()
        val languagePresentationModel = languageModelPresentationMapper.mapToPresentation(language)

        assertEqualData(language, languagePresentationModel)
    }

    private fun assertEqualData(language: Language, languagePresentationModel: LanguagePresentationModel) {
        assertEquals(language.iso639_1, languagePresentationModel.iso639_1)
        assertEquals(language.iso639_2, languagePresentationModel.iso639_2)
        assertEquals(language.name, languagePresentationModel.name)
        assertEquals(language.nativeName, languagePresentationModel.nativeName)
    }

}