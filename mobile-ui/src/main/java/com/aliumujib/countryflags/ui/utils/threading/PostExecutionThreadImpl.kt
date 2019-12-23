package com.aliumujib.countryflags.ui.utils.threading

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PostExecutionThreadImpl @Inject constructor(): PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}