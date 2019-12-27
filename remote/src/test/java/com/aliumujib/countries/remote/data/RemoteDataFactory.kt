package com.aliumujib.countries.remote.data

import com.aliumujib.countries.remote.countries.CountryRemoteModel
import com.aliumujib.countries.remote.countries.CurrencyRemoteModel
import com.aliumujib.countries.remote.countries.LanguageRemoteModel
import com.aliumujib.countryflags.data.model.CountryEntity
import com.aliumujib.countryflags.data.model.CurrencyEntity
import com.aliumujib.countryflags.data.model.LanguageEntity
import konveyor.base.randomBuild

object RemoteDataFactory {


    fun makeRandomLanguageRemoteModel(id: String = randomBuild()): LanguageRemoteModel {
        return LanguageRemoteModel(
            randomBuild(),
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeRandomCountryRemoteModel(id: String = randomBuild()): CountryRemoteModel {
        return randomBuild()
    }


    fun makeRandomCurrencyRemoteModel(id: String = randomBuild()): CurrencyRemoteModel {
        return CurrencyRemoteModel(
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeCurrencyEntity(id: String = randomBuild()): CurrencyEntity {
        return CurrencyEntity(
            randomBuild(),
            randomBuild(),
            id
        )
    }

    fun makeCountryEntity(): CountryEntity {
        return randomBuild()
    }

    fun makeLanguageEntity(): LanguageEntity {
        return randomBuild()
    }


    fun makeCountryEntityList(count: Int): List<CountryEntity> {
        val mutableList = mutableListOf<CountryEntity>()
        repeat(count) {
            mutableList.add(makeCountryEntity())
        }
        return mutableList
    }

    fun makeCountryRemoteModelList(count: Int): List<CountryRemoteModel> {
        val mutableList = mutableListOf<CountryRemoteModel>()
        repeat(count) {
            mutableList.add(makeRandomCountryRemoteModel())
        }
        return mutableList
    }

}