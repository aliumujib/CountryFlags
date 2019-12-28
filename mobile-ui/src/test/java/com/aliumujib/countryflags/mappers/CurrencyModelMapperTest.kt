package com.aliumujib.countryflags.mappers


import com.aliumujib.countryflags.data.UIDataFactory
import com.aliumujib.countryflags.models.CurrencyModel
import com.aliumujib.countryflags.presentation.models.CurrencyPresentationModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CurrencyModelMapperTest {


    private val currencyModelMapper: CurrencyModelMapper = CurrencyModelMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that CurrencyModel mapped to presentation CurrencyPresentationModel contains correct info`() {
        val currency: CurrencyModel = UIDataFactory.makeCurrencyModel()
        val currencyPresentationModel:CurrencyPresentationModel = currencyModelMapper.mapToPresentation(currency)
        assertEqualData(currency, currencyPresentationModel)
    }


    @Test
    fun `check that CurrencyPresentationModel mapped to domain CurrencyModel contains correct info`() {
        val currency: CurrencyPresentationModel = UIDataFactory.makeRandomCurrencyPresentationModel()
        val entity = currencyModelMapper.mapToView(currency)
        assertEqualData(entity, currency)
    }

    private fun assertEqualData(currency: CurrencyModel, presentation: CurrencyPresentationModel) {
        assertEquals(currency.name, presentation.name)
        assertEquals(currency.code, presentation.code)
        assertEquals(currency.symbol, presentation.symbol)
    }

}