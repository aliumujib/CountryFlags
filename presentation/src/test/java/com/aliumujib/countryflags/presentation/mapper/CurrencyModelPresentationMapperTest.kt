package com.aliumujib.countryflags.presentation.mapper


import com.aliumujib.countryflags.domain.models.Currency
import com.aliumujib.countryflags.presentation.data.PresentationDataFactory
import com.aliumujib.countryflags.presentation.mappers.CurrencyModelPresentationMapper
import com.aliumujib.countryflags.presentation.models.CurrencyPresentationModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CurrencyModelPresentationMapperTest {


    private val currencyModelMapper: CurrencyModelPresentationMapper = CurrencyModelPresentationMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that Currency mapped to presentation CurrencyPresentationModel contains correct info`() {
        val currency: Currency = PresentationDataFactory.makeCurrency()
        val currencyPresentationModel:CurrencyPresentationModel = currencyModelMapper.mapToPresentation(currency)
        assertEqualData(currency, currencyPresentationModel)
    }


    @Test
    fun `check that CurrencyPresentationModel mapped to domain Currency contains correct info`() {
        val currency: CurrencyPresentationModel = PresentationDataFactory.makeRandomCurrencyPresentationModel()
        val entity = currencyModelMapper.mapToDomain(currency)
        assertEqualData(entity, currency)
    }

    private fun assertEqualData(currency: Currency, presentation: CurrencyPresentationModel) {
        assertEquals(currency.name, presentation.name)
        assertEquals(currency.code, presentation.code)
        assertEquals(currency.symbol, presentation.symbol)
    }

}