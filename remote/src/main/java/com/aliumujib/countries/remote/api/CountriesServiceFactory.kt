package com.aliumujib.countries.remote.api

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object CountriesServiceFactory {

    open fun makeCountriesService(isDebug: Boolean, apiURL: String): CountriesAPI {
        val okHttpClient =
            makeOkHttpClient(
                makeLoggingInterceptor(
                    (isDebug)
                ),
                makeDispatcher()
            )
        return makeCountriesService(okHttpClient, apiURL)
    }

    private fun makeCountriesService(okHttpClient: OkHttpClient, apiURL: String): CountriesAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(apiURL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(CountriesAPI::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, dispatcher: Dispatcher): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BASIC
        } else {
            HttpLoggingInterceptor.Level.BODY
        }
        return logging
    }

    private fun makeDispatcher(): Dispatcher {
        val dispatcher = Dispatcher()
        dispatcher.maxRequestsPerHost = 10
        return dispatcher
    }
}