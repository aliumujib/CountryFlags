package com.aliumujib.countryflags.presentation.allcountries


import com.aliumujib.countryflags.domain.usecases.countries.FetchAllCountries
import com.aliumujib.countryflags.domain.usecases.countries.SearchCountries
import com.aliumujib.countryflags.presentation.mappers.CountryModelPresentationMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.lang.IllegalStateException
import javax.inject.Inject

class AllCountriesProcessorHolder @Inject constructor(
    private val fetchAllCountries: FetchAllCountries,
    private val searchCountries: SearchCountries,
    private val countriesMapper: CountryModelPresentationMapper
) {
    fun unsubscribe() {

    }

    private val loadAllCountriesProcessor =
        ObservableTransformer<AllCountriesAction.LoadAllCountriesAction, AllCountriesResult.LoadAllCountriesResults> {
            it.flatMap { action ->
                fetchAllCountries.execute(FetchAllCountries.Params.make(action.isConnected))
                    .map { countries ->
                        AllCountriesResult.LoadAllCountriesResults.Success(countries.map { country ->
                            countriesMapper.mapToPresentation(country)
                        })
                    }.cast(AllCountriesResult.LoadAllCountriesResults::class.java)
                    .onErrorReturn { error ->
                        AllCountriesResult.LoadAllCountriesResults.Error(error)
                    }.toObservable()
                    .startWith(AllCountriesResult.LoadAllCountriesResults.Loading)
            }
        }


    private val searchAllCountriesProcessor =
        ObservableTransformer<AllCountriesAction.SearchAllCountriesAction, AllCountriesResult.SearchAllCountriesResults> {
            it.flatMap {action->
                searchCountries.execute(SearchCountries.Params.make(action.query))
                    .map { data ->
                        AllCountriesResult.SearchAllCountriesResults.Success(data.map { country ->
                            countriesMapper.mapToPresentation(country)
                        })
                    }.cast(AllCountriesResult.SearchAllCountriesResults::class.java)
                    .onErrorReturn { error ->
                        AllCountriesResult.SearchAllCountriesResults.Error(error)
                    }.toObservable()
                    .startWith(AllCountriesResult.SearchAllCountriesResults.Refreshing)
            }
        }


    internal val actionProcessor =
        ObservableTransformer<AllCountriesAction, AllCountriesResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(AllCountriesAction.LoadAllCountriesAction::class.java).compose(
                        loadAllCountriesProcessor
                    ),
                    shared.ofType(AllCountriesAction.SearchAllCountriesAction::class.java).compose(
                        searchAllCountriesProcessor
                    )
                )
                    .mergeWith(
                        shared.filter { action ->
                            action !is AllCountriesAction.LoadAllCountriesAction && action !is AllCountriesAction.SearchAllCountriesAction
                        }.flatMap { type ->
                            Observable.error<AllCountriesResult> {
                                IllegalStateException("Unknown action type ${type::class.java.simpleName} cannot be handled.")
                            }
                        }
                    )
            }
        }

}