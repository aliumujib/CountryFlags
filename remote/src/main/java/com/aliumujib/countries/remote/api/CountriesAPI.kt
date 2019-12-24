package com.aliumujib.countries.remote.api


import com.aliumujib.countries.remote.countries.CountryRemoteModel
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesAPI {

    @GET("all")
    fun fetchAllCountries(): Maybe<List<CountryRemoteModel>>

    @GET("name/{name}")
    fun searchCountriesByName(@Path("name") name: String): Maybe<List<CountryRemoteModel>>

}
