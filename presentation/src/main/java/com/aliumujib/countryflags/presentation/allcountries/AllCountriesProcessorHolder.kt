package com.aliumujib.countryflags.presentation.allcountries


import com.aliumujib.countryflags.domain.usecases.countries.FetchAllCountries
import com.aliumujib.countryflags.domain.usecases.countries.RefreshCountries
import com.aliumujib.countryflags.presentation.mappers.CountryModelPresentationMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.lang.IllegalStateException
import javax.inject.Inject

class AllCountriesProcessorHolder @Inject constructor(
    private val fetchAllCountries: FetchAllCountries,
    private val refreshCountries: RefreshCountries,
    private val countriesMapper: CountryModelPresentationMapper
) {
    fun unsubscribe() {

    }

    private val loadAllCountriesProcessor =
        ObservableTransformer<AllCountriesAction.LoadAllCountriesAction, AllCountriesResult.LoadAllCountriesResults> {
            it.flatMap {
                fetchAllCountries.execute().map { countries ->
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


    private val refreshAllCountriesProcessor =
        ObservableTransformer<AllCountriesAction.RefreshAllCountriesAction, AllCountriesResult.RefreshAllCountriesResults> {
            it.flatMap {
                refreshCountries.execute()
                    .toMaybe<Boolean>()
                    .map { _ ->
                        AllCountriesResult.RefreshAllCountriesResults.Success
                    }.cast(AllCountriesResult.RefreshAllCountriesResults::class.java)
                    .onErrorReturn { error ->
                        AllCountriesResult.RefreshAllCountriesResults.Error(error)
                    }.toObservable()
                    .startWith(AllCountriesResult.RefreshAllCountriesResults.Refreshing)
            }
        }


    internal val actionProcessor =
        ObservableTransformer<AllCountriesAction, AllCountriesResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(AllCountriesAction.LoadAllCountriesAction::class.java).compose(
                        loadAllCountriesProcessor
                    ),
                    shared.ofType(AllCountriesAction.RefreshAllCountriesAction::class.java).compose(
                        refreshAllCountriesProcessor
                    )
                )
                    .mergeWith(
                        shared.filter { action ->
                            action !is AllCountriesAction.LoadAllCountriesAction && action !is AllCountriesAction.RefreshAllCountriesAction
                        }.flatMap { type ->
                            Observable.error<AllCountriesResult> {
                                IllegalStateException("Unknown action type ${type::class.java.simpleName} cannot be handled.")
                            }
                        }
                    )
            }
        }

}