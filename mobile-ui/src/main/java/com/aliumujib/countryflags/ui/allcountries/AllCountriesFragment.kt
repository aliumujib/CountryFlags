package com.aliumujib.countryflags.ui.allcountries

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliumujib.countryflags.R
import com.aliumujib.countryflags.domain.usecases.countries.SearchCountries
import com.aliumujib.countryflags.mappers.CountryModelMapper
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesAction
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesIntent
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesViewModel
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesViewState
import com.aliumujib.countryflags.presentation.mvibase.MVIView
import com.aliumujib.countryflags.ui.adapters.allcountries.AllCountriesAdapter
import com.aliumujib.countryflags.ui.inject.ViewModelFactory
import com.aliumujib.countryflags.ui.utils.ext.dpToPx
import com.aliumujib.countryflags.ui.utils.ext.visible
import com.aliumujib.countryflags.ui.utils.views.DividerItemDecoration
import com.aliumujib.countryflags.ui.utils.views.SpacingItemDecoration
import com.jakewharton.rxbinding3.appcompat.queryTextChangeEvents
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.search_fragment.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class AllCountriesFragment : DaggerFragment(), MVIView<AllCountriesIntent, AllCountriesViewState> {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var countryModelMapper: CountryModelMapper

    @Inject
    lateinit var rvAdapter: AllCountriesAdapter

    private val disposables = CompositeDisposable()

    private val searchQuerySubject = PublishSubject.create<String>()

    private val closeSearchSubject = PublishSubject.create<Unit>()


    private val allCountriesViewModel: AllCountriesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AllCountriesViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    private fun loadIntent(): Observable<AllCountriesIntent.LoadAllCountriesIntent> {
        return Observable.just(AllCountriesIntent.LoadAllCountriesIntent(checkIfIsConnectedPhone()))
    }

    private fun closeSearchIntent(): Observable<AllCountriesIntent.LoadAllCountriesIntent> {
        return closeSearchSubject.flatMap {
            loadIntent()
        }
    }

    private fun searchIntents(): Observable<AllCountriesIntent.SearchAllCountriesIntent> {
        return searchQuerySubject.map {
            AllCountriesIntent.SearchAllCountriesIntent(it)
        }
    }

    private fun refreshIntent(): Observable<AllCountriesIntent.LoadAllCountriesIntent> {
        return swipe_refresh_layout.refreshes().flatMap {
            loadIntent()
        }.startWith(AllCountriesIntent.LoadAllCountriesIntent(checkIfIsConnectedPhone()))
    }

    private fun checkIfIsConnectedPhone(): Boolean {
        val cm =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
            ?: // There are no active networks.
            return false
        return ni.isConnected
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allCountriesViewModel.processIntents(intents())

        recycler_view.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context
                )
            )
            addItemDecoration(
                SpacingItemDecoration(
                    context.dpToPx(16),
                    context.dpToPx(0),
                    doubleFirstItemLeftMargin = false,
                    isVertical = true
                )
            )
        }

    }

    override fun onStart() {
        super.onStart()
        bind()
    }

    private fun bind() {
        disposables.add(allCountriesViewModel.states().subscribe(this::render))
        allCountriesViewModel.processIntents(intents())
    }

    override fun render(state: AllCountriesViewState) {
        swipe_refresh_layout.post {
            swipe_refresh_layout.isRefreshing = state.isLoading
        }
        emptyView.visible = state.data.isEmpty() && state.isLoading.not() && (state.error == null)
        errorView.visible = (state.error != null) && (state.isLoading.not())
        recycler_view.visible = state.data.isNotEmpty()
        rvAdapter.updateData(state.data.map {
            countryModelMapper.mapToView(it)
        })
        state.error?.let { error ->
            errorView.text = error.message
            Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_toolbar, menu)
        disposables.add((menu?.findItem(R.id.app_search)?.actionView as SearchView).queryTextChangeEvents()
            .filter {
                it.queryText.isNotEmpty()
            }
            .subscribe {
                searchQuerySubject.onNext(it.queryText.toString())
            })

        (menu.findItem(R.id.app_search)?.actionView as SearchView).setOnCloseListener {
            closeSearchSubject.onNext(Unit)
            return@setOnCloseListener true
        }
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun intents(): Observable<AllCountriesIntent> {
        return Observable.merge(
            loadIntent(),
            refreshIntent(),
            searchIntents(),
            closeSearchIntent()
        )
    }

}