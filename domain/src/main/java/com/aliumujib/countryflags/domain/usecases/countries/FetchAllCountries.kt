package com.aliumujib.countryflags.domain.usecases.countries


import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.domain.usecases.base.MaybeUseCase
import io.reactivex.Maybe
import javax.inject.Inject

open class FetchAllCountries @Inject constructor(
    private val countriesRepository: ICountriesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : MaybeUseCase<List<Country>, FetchAllCountries.Params>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseMaybe(params: Params?): Maybe<List<Country>> {
        return params?.let {
            countriesRepository.fetchCountries(params.isConnected)
        } ?: throw IllegalArgumentException("Parameters cannot be null")
    }

    data class Params(val isConnected: Boolean) {
        companion object {

            fun make(isConnected: Boolean): Params {
                return Params(isConnected)
            }
        }
    }


}