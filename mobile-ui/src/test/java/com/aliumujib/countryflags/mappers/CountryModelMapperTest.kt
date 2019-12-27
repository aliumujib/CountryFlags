package com.aliumujib.countryflags.mappers


import com.aliumujib.countryflags.data.UIDataFactory
import com.aliumujib.countryflags.models.CountryModel
import com.aliumujib.countryflags.presentation.models.CountryPresentationModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class CountryModelMapperTest {

    private val currencyModelMapper = CurrencyModelMapper()
    private val languageModelMapper = LanguageModelMapper()

    private val countryModelMapper =
        CountryModelMapper(
            languageModelMapper,
            currencyModelMapper
        )


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that CountryPresentationModel mapped to CountryModel maps correct info`() {
        val countryPresentationModel = UIDataFactory.makeRandomCountryPresentationModel()
        val country = countryModelMapper.mapToView(countryPresentationModel)
        assertEqualData(country, countryPresentationModel)
    }


    @Test
    fun `check that CountryModel mapped from CountryPresentationModel maps correct info`() {
        val country = UIDataFactory.makeCountryModel()
        val countryPresentationModel = countryModelMapper.mapToPresentation(country)

        assertEqualData(country, countryPresentationModel)
    }

    private fun assertEqualData(
        country: CountryModel,
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