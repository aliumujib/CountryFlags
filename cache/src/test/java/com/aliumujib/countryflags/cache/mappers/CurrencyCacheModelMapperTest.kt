package com.aliumujib.countryflags.cache.mappers

import com.aliumujib.countryflags.cache.data.CacheDataFactory
import com.aliumujib.countryflags.cache.mapper.CurrencyCacheModelMapper
import com.aliumujib.countryflags.cache.models.CurrencyCacheModel
import com.aliumujib.countryflags.data.model.CurrencyEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CurrencyCacheModelMapperTest {


    var currencyCacheModelMapper: CurrencyCacheModelMapper = CurrencyCacheModelMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `check that CurrencyEntity mapped to domain Currency contains correct info`() {
        var entity: CurrencyEntity = CacheDataFactory.makeRandomCurrencyEntity()
        var planet = currencyCacheModelMapper.mapToModel(entity)
        assertEqualData(entity, planet)
    }


    @Test
    fun `check that Currency mapped to data CurrencyEntity contains correct info`() {
        var currency: CurrencyCacheModel = CacheDataFactory.makeRandomCurrencyCacheModel()
        var entity = currencyCacheModelMapper.mapFromModel(currency)
        assertEqualData(entity, currency)
    }

    private fun assertEqualData(entity: CurrencyEntity, cache: CurrencyCacheModel) {
        assertEquals(entity.name, cache.name)
        assertEquals(entity.code, cache.code)
        assertEquals(entity.symbol, cache.symbol)
    }

}