package com.aliumujib.countryflags.cache.converters

import androidx.room.TypeConverter
import com.aliumujib.countryflags.cache.models.CountryCacheModel
import com.aliumujib.countryflags.cache.models.CurrencyCacheModel
import com.aliumujib.countryflags.cache.models.LanguageCacheModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromListString(value: String?): List<String>? {
        val listType = object : TypeToken<ArrayList<String>?>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromStringArrayList(list: List<String>?): String {
        return Gson().toJson(list)
    }


    @TypeConverter
    fun fromDoubleArrayList(list: List<Double>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromListDouble(value: String?): List<Double>? {
        val listType = object : TypeToken<ArrayList<Double>?>() {}.type
        return Gson().fromJson(value, listType)
    }


    @TypeConverter
    fun fromListCurrencyCacheModel(value: String?): List<CurrencyCacheModel>? {
        val listType = object : TypeToken<ArrayList<CurrencyCacheModel>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCurrencyCacheModelArrayList(list: List<CurrencyCacheModel>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromListCountryCacheModel(value: String?): List<CountryCacheModel>? {
        val listType = object : TypeToken<ArrayList<CountryCacheModel>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCountryCacheModelArrayList(list: List<CountryCacheModel>?): String {
        return Gson().toJson(list)
    }


    @TypeConverter
    fun fromListLanguageCacheModel(value: String?): List<LanguageCacheModel>? {
        val listType = object : TypeToken<ArrayList<LanguageCacheModel>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromLanguageCacheModelArrayList(list: List<LanguageCacheModel>?): String {
        return Gson().toJson(list)
    }

}