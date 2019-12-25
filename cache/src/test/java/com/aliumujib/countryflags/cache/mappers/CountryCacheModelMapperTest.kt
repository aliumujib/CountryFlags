package com.aliumujib.countryflags.cache.mappers

import com.aliumujib.countryflags.cache.data.CacheDataFactory
import com.aliumujib.countryflags.cache.mapper.CountryCacheModelMapper
import com.aliumujib.countryflags.cache.mapper.CurrencyCacheModelMapper
import com.aliumujib.countryflags.cache.mapper.LanguageCacheModelMapper
import com.aliumujib.countryflags.cache.models.CountryCacheModel
import com.aliumujib.countryflags.data.model.CountryEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CountryCacheModelMapperTest {

    private val currencyEntityMapper = CurrencyCacheModelMapper()
    private val languageEntityMapper = LanguageCacheModelMapper()

    private val countryCacheModelMapper =
        CountryCacheModelMapper(currencyEntityMapper, languageEntityMapper)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that CountryEntity mapped to Country maps correct info`() {
        val entity = CacheDataFactory.makeRandomCountry()
        val country = countryCacheModelMapper.mapToEntity(entity)

        assertEqualData(entity, country)
    }


    @Test
    fun `check that Country mapped from CountryEntity maps correct info`() {
        val character = CacheDataFactory.makeCountryCacheModel()
        val characterEntity = countryCacheModelMapper.mapFromEntity(character)

        assertEqualData(characterEntity, character)
    }

    private fun assertEqualData(entity: CountryEntity, character: CountryCacheModel) {
        assertEquals(entity.alpha2Code, character.alpha2Code)
        assertEquals(entity.alpha3Code, character.alpha3Code)
        assertEquals(entity.area, character.area)
        assertEquals(entity.callingCodes, character.callingCodes)
        assertEquals(entity.capital, character.capital)
        assertEquals(entity.cioc, character.cioc)
        assertEquals(entity.demonym, character.demonym)
        assertEquals(entity.flag, character.flag)
        assertEquals(entity.latlng, character.latlng)
        assertEquals(entity.name, character.name)
        assertEquals(entity.nativeName, character.nativeName)
        assertEquals(entity.numericCode, character.numericCode)
        assertEquals(entity.population, character.population)
    }

}