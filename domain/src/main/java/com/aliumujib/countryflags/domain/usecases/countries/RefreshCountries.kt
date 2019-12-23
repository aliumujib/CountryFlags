package com.aliumujib.countryflags.domain.usecases.countries


import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.domain.usecases.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

open class RefreshCountries @Inject constructor(
    private val characterRepository: ICountriesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Unit?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Unit?): Completable {
        return characterRepository.refreshCountries()
    }

}