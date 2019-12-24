package com.aliumujib.countryflags.domain.usecases.base

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a UseCase that returns an instance of a [Flowable].
 */
abstract class SingleUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread) {

    /**
     * Builds a [Flowable] which will be used when the current [SingleUseCase] is executed.
     */
    protected abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    /**
     * Executes the current use case.
     */
    open fun execute(params: Params? = null): Single<T> {
        return this.buildUseCaseSingle(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

}