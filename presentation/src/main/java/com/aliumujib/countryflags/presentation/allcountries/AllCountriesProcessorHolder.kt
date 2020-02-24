package com.aliumujib.countryflags.presentation.allcountries


import com.aliumujib.countryflags.domain.usecases.countries.FetchAllCountries
import com.aliumujib.countryflags.domain.usecases.countries.SearchCountries
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesAction.*
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesResult.*
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
        ObservableTransformer<LoadAllCountriesAction, LoadAllCountriesResults> {
            it.flatMap { action ->
                fetchAllCountries.execute(FetchAllCountries.Params.make(action.isConnected))
                    .map { countries ->
                        LoadAllCountriesResults.Success(countries.map { country ->
                            countriesMapper.mapToPresentation(country)
                        })
                    }.cast(LoadAllCountriesResults::class.java)
                    .onErrorReturn { error ->
                        LoadAllCountriesResults.Error(error)
                    }.toObservable()
                    .startWith(LoadAllCountriesResults.Loading)
            }
        }


    private val searchAllCountriesProcessor =
        ObservableTransformer<SearchAllCountriesAction, SearchAllCountriesResults> {
            it.flatMap { action ->
                searchCountries.execute(SearchCountries.Params.make(action.query))
                    .map { data ->
                        SearchAllCountriesResults.Success(data.map { country ->
                            countriesMapper.mapToPresentation(country)
                        })
                    }.cast(SearchAllCountriesResults::class.java)
                    .onErrorReturn { error ->
                        SearchAllCountriesResults.Error(error)
                    }.toObservable()
                    .startWith(SearchAllCountriesResults.Refreshing)
            }
        }


    internal val actionProcessor =
        ObservableTransformer<AllCountriesAction, AllCountriesResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(LoadAllCountriesAction::class.java).compose(
                        loadAllCountriesProcessor
                    ),
                    shared.ofType(SearchAllCountriesAction::class.java).compose(
                        searchAllCountriesProcessor
                    )
                )
                    .mergeWith(
                        shared.filter { action ->
                            action !is LoadAllCountriesAction && action !is SearchAllCountriesAction
                        }.flatMap { type ->
                            Observable.error<AllCountriesResult> {
                                IllegalStateException("Unknown action type ${type::class.java.simpleName} cannot be handled.")
                            }
                        }
                    )
            }
        }

}