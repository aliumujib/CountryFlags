package com.aliumujib.countryflags.mappers


import com.aliumujib.countryflags.data.UIDataFactory
import com.aliumujib.countryflags.models.LanguageModel
import com.aliumujib.countryflags.presentation.models.LanguagePresentationModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class LanguageModelMapperTest {

    private val languageModelMapper: LanguageModelMapper = LanguageModelMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that LanguagePresentationModel mapped to cache LanguageModel contains correct info`() {
        val languagePresentationModel: LanguagePresentationModel = UIDataFactory.makeRandomLanguagePresentationModel()
        val language = languageModelMapper.mapToView(languagePresentationModel)
        assertEqualData(language, languagePresentationModel)
    }


    @Test
    fun `check that LanguageModel mapped to data LanguagePresentationModel contains correct info`() {
        val language: LanguageModel = UIDataFactory.makeLanguageModel()
        val languagePresentationModel = languageModelMapper.mapToPresentation(language)
        assertEqualData(language, languagePresentationModel)
    }

    private fun assertEqualData(language: LanguageModel, languagePresentationModel: LanguagePresentationModel) {
        assertEquals(language.iso639_1, languagePresentationModel.iso639_1)
        assertEquals(language.iso639_2, languagePresentationModel.iso639_2)
        assertEquals(language.name, languagePresentationModel.name)
        assertEquals(language.nativeName, languagePresentationModel.nativeName)
    }

}