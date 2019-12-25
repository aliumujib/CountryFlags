package com.aliumujib.countryflags.presentation.allcountries

import androidx.lifecycle.ViewModel
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesResult.*
import com.aliumujib.countryflags.presentation.ext.notOfType
import com.aliumujib.countryflags.presentation.mvibase.MVIViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class AllCountriesViewModel @Inject constructor(
    private val processorHolder: AllCountriesProcessorHolder
) : ViewModel(), MVIViewModel<AllCountriesIntent, AllCountriesViewState> {

    private val intentsSubject = PublishSubject.create<AllCountriesIntent>()

    private val intentFilter =
        ObservableTransformer<AllCountriesIntent, AllCountriesIntent> { intents ->
            intents.publish { shared ->
                Observable.merge(
                    shared.ofType(AllCountriesIntent.LoadAllCountriesIntent::class.java),
                    shared.notOfType(AllCountriesIntent.LoadAllCountriesIntent::class.java)
                )
            }
        }

    private val statesObservable: Observable<AllCountriesViewState> = compose()

    private fun compose(): Observable<AllCountriesViewState> {
        return intentsSubject.compose(intentFilter)
            .map(this::actionFromIntent)
            .compose(processorHolder.actionProcessor)
            .scan(AllCountriesViewState.idle(), reducer)
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
        }
    }

    override fun processIntents(observable: Observable<AllCountriesIntent>) {
        observable.subscribe(intentsSubject)
    }

    override fun states(): Observable<AllCountriesViewState> = statesObservable


    companion object {

        private val reducer =
            BiFunction { previousState: AllCountriesViewState, result: AllCountriesResult ->
                when (result) {
                    is LoadAllCountriesResults -> {
                        when (result) {
                            is LoadAllCountriesResults.Success -> previousState.copy(
                                isLoading = false,
                                data = result.data,
                                error = null
                            )
                            is LoadAllCountriesResults.Error -> previousState.copy(
                                isLoading = false,
                                error = result.error
                            )
                            is LoadAllCountriesResults.Loading -> previousState.copy(
                                isLoading = true
                            )
                        }
                    }
                    is SearchAllCountriesResults -> {
                        when (result) {
                            is SearchAllCountriesResults.Success -> previousState.copy(
                                data = result.data,
                                isLoading = false,
                                error = null
                            )
                            is SearchAllCountriesResults.Error -> previousState.copy(
                                isLoading = false,
                                error = result.error
                            )
                            is SearchAllCountriesResults.Refreshing -> previousState.copy(
                                isLoading = true
                            )
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