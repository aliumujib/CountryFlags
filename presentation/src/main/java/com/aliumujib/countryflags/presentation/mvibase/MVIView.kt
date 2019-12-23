package com.aliumujib.countryflags.presentation.mvibase

import io.reactivex.Observable


interface MVIView<I : MVIIntent, in S : MVIViewState> {

    fun render(state: S)

    fun intents(): Observable<I>

}
