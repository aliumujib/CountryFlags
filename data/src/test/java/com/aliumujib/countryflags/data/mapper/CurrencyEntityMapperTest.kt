package com.aliumujib.countryflags.data.mapper

import com.aliumujib.countryflags.data.DummyDataFactory
import com.aliumujib.countryflags.data.model.CurrencyEntity
import com.aliumujib.countryflags.domain.models.Currency
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CurrencyEntityMapperTest {


    private var currencyEntityMapper: CurrencyEntityMapper = CurrencyEntityMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that CurrencyEntity mapped to domain Currency contains correct info`() {
        var entity: CurrencyEntity = DummyDataFactory.makeRandomCurrencyEntity()
        var planet = currencyEntityMapper.mapFromEntity(entity)
        assertEqualData(entity, planet)
    }


    @Test
    fun `check that Currency mapped to data CurrencyEntity contains correct info`() {
        var currency: Currency = DummyDataFactory.makeRandomCurrency()
        var entity = currencyEntityMapper.mapToEntity(currency)
        assertEqualData(entity, currency)
    }

    private fun assertEqualData(entity: CurrencyEntity, domain: Currency) {
        assertEquals(entity.name, domain.name)
        assertEquals(entity.code, domain.code)
        assertEquals(entity.symbol, domain.symbol)
    }

}