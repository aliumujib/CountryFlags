package com.aliumujib.countryflags.domain.usecases.base

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Abstract class for a UseCase that returns an instance of a [Flowable].
 */
abstract class FlowableUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread) {

    /**
     * Builds a [Flowable] which will be used when the current [FlowableUseCase] is executed.
     */
    protected abstract fun buildUseCaseFlowable(params: Params? = null): Flowable<T>

    /**
     * Executes the current use case.
     */
    open fun execute(params: Params? = null): Flowable<T> {
        return this.buildUseCaseFlowable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

}