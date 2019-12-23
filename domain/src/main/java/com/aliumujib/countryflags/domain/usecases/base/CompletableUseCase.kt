package com.aliumujib.countryflags.domain.usecases.base

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a UseCase that returns an instance of a [Completable].
 */
abstract class CompletableUseCase<in Params> protected constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread) {

    private val subscription = Disposables.empty()

    /**
     * Builds a [Completable] which will be used when the current [CompletableUseCase] is executed.
     */
    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    /**
     * Executes the current use case.
     */
    fun execute(params: Params? = null): Completable {
        return this.buildUseCaseCompletable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

    /**
     * Unsubscribes from current [Disposable].
     */
    fun unsubscribe() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }

}