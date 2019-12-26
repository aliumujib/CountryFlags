package com.aliumujib.countryflags.presentation.allcountries

import androidx.lifecycle.ViewModel
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesResult.*
import com.aliumujib.countryflags.presentation.ext.notOfType
import com.aliumujib.countryflags.presentation.mvibase.MVIViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AllCountriesViewModel @Inject constructor(
    private val processorHolder: AllCountriesProcessorHolder
) : ViewModel(), MVIViewModel<AllCountriesIntent, AllCountriesViewState> {

    private val intentsSubject = PublishSubject.create<AllCountriesIntent>()

    private val intentFilter =
        ObservableTransformer<AllCountriesIntent, AllCountriesIntent> { intents ->
            intents.publish { shared ->
                Observable.merge(
                    shared.ofType(AllCountriesIntent.LoadAllCountriesIntent::class.java).take(1),
                    shared.notOfType(AllCountriesIntent.LoadAllCountriesIntent::class.java)
                )
            }
        }

    private val statesObservable: Observable<AllCountriesViewState> = compose()

    private fun compose(): Observable<AllCountriesViewState> {
        return intentsSubject.compose(intentFilter)
            .map(this::actionFromIntent)
            .compose(processorHolder.actionProcessor)
            .scan<AllCountriesViewState>(AllCountriesViewState.Idle, reducer)
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
    }


    private fun actionFromIntent(intent: AllCountriesIntent): AllCountriesAction {
        return when (intent) {
            is AllCountriesIntent.LoadAllCountriesIntent -> AllCountriesAction.LoadAllCountriesAction(
                intent.isOnline
            )
            is AllCountriesIntent.SearchAllCountriesIntent -> AllCountriesAction.SearchAllCountriesAction(
                intent.query
            )
            is AllCountriesIntent.RefreshAllCountriesIntent -> AllCountriesAction.LoadAllCountriesAction(
                intent.isOnline
            )
        }
    }

    override fun processIntents(observable: Observable<AllCountriesIntent>) {
        observable.subscribe(intentsSubject)
    }

    override fun states(): Observable<AllCountriesViewState> = statesObservable


    companion object {

        private val reducer =
            BiFunction { _: AllCountriesViewState, result: AllCountriesResult ->
                when (result) {
                    is LoadAllCountriesResults -> {
                        when (result) {
                            is LoadAllCountriesResults.Success -> AllCountriesViewState.Success(
                                result.data
                            )
                            is LoadAllCountriesResults.Error -> AllCountriesViewState.Error(
                                result.error
                            )
                            is LoadAllCountriesResults.Loading -> AllCountriesViewState.Loading
                        }
                    }
                    is SearchAllCountriesResults -> {
                        when (result) {
                            is SearchAllCountriesResults.Success -> AllCountriesViewState.Success(
                                result.data
                            )
                            is SearchAllCountriesResults.Error -> AllCountriesViewState.Error(result.error)
                            is SearchAllCountriesResults.Refreshing -> AllCountriesViewState.Loading
                        }
                    }
                }
            }
    }


    override fun onCleared() {
        super.onCleared()
        processorHolder.unsubscribe()
    }

}