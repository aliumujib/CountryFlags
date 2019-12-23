package com.aliumujib.countryflags.domain.usecases.countries


import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.domain.usecases.base.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

open class FetchAllCountries @Inject constructor(
    private val characterRepository: ICountriesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<Country>, Unit>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseFlowable(params: Unit?): Flowable<List<Country>> {
        return characterRepository.fetchCountries()
    }

}