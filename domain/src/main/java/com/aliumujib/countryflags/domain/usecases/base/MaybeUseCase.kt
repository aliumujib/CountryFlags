package com.aliumujib.countryflags.domain.usecases.base

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a UseCase that returns an instance of a [Flowable].
 */
abstract class MaybeUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread) {

    /**
     * Builds a [Flowable] which will be used when the current [MaybeUseCase] is executed.
     */
    protected abstract fun buildUseCaseMaybe(params: Params? = null): Maybe<T>

    /**
     * Executes the current use case.
     */
    open fun execute(params: Params? = null): Maybe<T> {
        return this.buildUseCaseMaybe(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

}