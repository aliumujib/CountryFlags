package com.aliumujib.countryflags.presentation.mvibase

import io.reactivex.Observable


interface MVIViewModel<I : MVIIntent, S : MVIViewState> {

    fun processIntents(observable: Observable<I>)

    fun states(): Observable<S>

}
