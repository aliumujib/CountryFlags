package com.aliumujib.countryflags.domain.util

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestPostExecutionThread : PostExecutionThread {
    override val scheduler: Scheduler
        get() = Schedulers.io()
}