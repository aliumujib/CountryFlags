package com.aliumujib.countries.remote.api


import com.aliumujib.countries.remote.countries.CountryRemoteModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesAPI {

    @GET("all")
    fun fetchAllCountries(): Single<List<CountryRemoteModel>>

}
