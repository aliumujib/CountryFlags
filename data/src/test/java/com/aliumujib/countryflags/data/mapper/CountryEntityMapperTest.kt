package com.aliumujib.countryflags.data.mapper

import com.aliumujib.countryflags.data.DummyDataFactory
import com.aliumujib.countryflags.data.model.CountryEntity
import com.aliumujib.countryflags.domain.models.Country
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CountryEntityMapperTest {

    private val currencyEntityMapper = CurrencyEntityMapper()
    private val languageEntityMapper = LanguageEntityMapper()

    private val countryEntityMapper = CountryEntityMapper(currencyEntityMapper, languageEntityMapper)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that CountryEntity mapped to Country maps correct info`() {
        val countryEntity = DummyDataFactory.makeRandomCountryEntity()
        val country = countryEntityMapper.mapFromEntity(countryEntity)

        assertEqualData(countryEntity, country)
    }


    @Test
    fun `check that Country mapped from CountryEntity maps correct info`() {
        val character = DummyDataFactory.makeRandomCountry()
        val characterEntity = countryEntityMapper.mapToEntity(character)

        assertEqualData(characterEntity, character)
    }

    private fun assertEqualData(entity: CountryEntity, character: Country) {
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