package com.aliumujib.countryflags.domain.usecases.countries


import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.domain.usecases.base.MaybeUseCase
import io.reactivex.Maybe
import javax.inject.Inject

open class SearchCountries @Inject constructor(
    private val countryRepository: ICountriesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : MaybeUseCase<List<Country>, SearchCountries.Params?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseMaybe(params: Params?): Maybe<List<Country>> {
        return params?.let {
            countryRepository.searchCountries(params.query)
        } ?: throw IllegalArgumentException("Parameters cannot be null")
    }

    data class Params(val query: String) {
        companion object {
            fun make(query: String): Params {
                return Params(query)
            }
        }
    }

}