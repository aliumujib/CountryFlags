package com.aliumujib.countryflags.cache.mappers

import com.aliumujib.countryflags.cache.data.CacheDataFactory
import com.aliumujib.countryflags.cache.mapper.LanguageCacheModelMapper
import com.aliumujib.countryflags.cache.models.LanguageCacheModel
import com.aliumujib.countryflags.data.model.LanguageEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class LanguageCacheModelMapperTest {

    private val languageCacheModelMapper: LanguageCacheModelMapper = LanguageCacheModelMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that LanguageEntity mapped to cache LanguageCacheModel contains correct info`() {
        val languageEntity: LanguageEntity = CacheDataFactory.makeRandomLanguageEntity()
        val languageCacheModel = languageCacheModelMapper.mapToModel(languageEntity)
        assertEqualData(languageEntity, languageCacheModel)
    }


    @Test
    fun `check that LanguageCacheModel mapped to data LanguageEntity contains correct info`() {
        val languageCacheModel: LanguageCacheModel = CacheDataFactory.makeRandomLanguageCacheModel()
        val languageEntity = languageCacheModelMapper.mapFromModel(languageCacheModel)

        assertEqualData(languageEntity, languageCacheModel)
    }

    private fun assertEqualData(entity: LanguageEntity, cache: LanguageCacheModel) {
        assertEquals(entity.iso639_1, cache.iso639_1)
        assertEquals(entity.iso639_2, cache.iso639_2)
        assertEquals(entity.name, cache.name)
        assertEquals(entity.nativeName, cache.nativeName)
    }

}