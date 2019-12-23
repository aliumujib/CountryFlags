package com.aliumujib.countryflags.data.mapper

import com.aliumujib.countryflags.data.DummyDataFactory
import com.aliumujib.countryflags.data.model.LanguageEntity
import com.aliumujib.countryflags.domain.models.Language
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class LanguageEntityMapperTest {

    var entityMapper: LanguageEntityMapper = LanguageEntityMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that LanguageEntity mapped to domain Language contains correct info`() {
        val languageEntity: LanguageEntity = DummyDataFactory.makeRandomLanguageEntity()
        val language = entityMapper.mapFromEntity(languageEntity)

        assertEqualData(languageEntity, language)
    }


    @Test
    fun `check that Language mapped to data LanguageEntity contains correct info`() {
        val language: Language = DummyDataFactory.makeRandomLanguage()
        val languageEntity = entityMapper.mapToEntity(language)

        assertEqualData(languageEntity, language)
    }

    private fun assertEqualData(entity: LanguageEntity, domain: Language) {
        assertEquals(entity.iso639_1, domain.iso639_1)
        assertEquals(entity.iso639_2, domain.iso639_2)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.nativeName, domain.nativeName)
    }

}