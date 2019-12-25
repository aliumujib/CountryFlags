package com.aliumujib.countryflags.presentation.mapper


import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.presentation.data.PresentationDataFactory
import com.aliumujib.countryflags.presentation.mappers.CountryModelPresentationMapper
import com.aliumujib.countryflags.presentation.mappers.CurrencyModelPresentationMapper
import com.aliumujib.countryflags.presentation.mappers.LanguageModelPresentationMapper
import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CountryModelPresentationMapperTest {

    private val currencyModelPresentationMapper = CurrencyModelPresentationMapper()
    private val languageModelPresentationMapper = LanguageModelPresentationMapper()

    private val countryModelPresentationMapper =
        CountryModelPresentationMapper(
            languageModelPresentationMapper,
            currencyModelPresentationMapper
        )


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that CountryPresentationModel mapped to Country maps correct info`() {
        val countryPresentationModel = PresentationDataFactory.makeRandomCountryPresentationModel()
        val country = countryModelPresentationMapper.mapToDomain(countryPresentationModel)
        assertEqualData(country, countryPresentationModel)
    }


    @Test
    fun `check that Country mapped from CountryPresentationModel maps correct info`() {
        val country = PresentationDataFactory.makeCountry()
        val countryPresentationModel = countryModelPresentationMapper.mapToPresentation(country)

        assertEqualData(country, countryPresentationModel)
    }

    private fun assertEqualData(
        country: Country,
        countryPresentationModel: CountryPresentationModel
    ) {
        assertEquals(country.alpha2Code, countryPresentationModel.alpha2Code)
        assertEquals(country.alpha3Code, countryPresentationModel.alpha3Code)
        assertEquals(country.area, countryPresentationModel.area)
        assertEquals(country.callingCodes, countryPresentationModel.callingCodes)
        assertEquals(country.capital, countryPresentationModel.capital)
        assertEquals(country.cioc, countryPresentationModel.cioc)
        assertEquals(country.demonym, countryPresentationModel.demonym)
        assertEquals(country.flag, countryPresentationModel.flag)
        assertEquals(country.latlng, countryPresentationModel.latlng)
        assertEquals(country.name, countryPresentationModel.name)
        assertEquals(country.nativeName, countryPresentationModel.nativeName)
        assertEquals(country.numericCode, countryPresentationModel.numericCode)
        assertEquals(country.population, countryPresentationModel.population)
    }

}