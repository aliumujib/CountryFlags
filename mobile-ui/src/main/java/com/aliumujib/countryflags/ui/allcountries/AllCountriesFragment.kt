package com.aliumujib.countryflags.ui.allcountries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliumujib.countryflags.R
import com.aliumujib.countryflags.mappers.CountryModelMapper
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
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject


class AllCountriesFragment : DaggerFragment(), MVIView<AllCountriesIntent, AllCountriesViewState> {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var countryModelMapper: CountryModelMapper

    @Inject
    lateinit var rvAdapter: AllCountriesAdapter

    private val disposables = CompositeDisposable()

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
        return Observable.just(AllCountriesIntent.LoadAllCountriesIntent)
    }

    private fun refreshIntent(): Observable<AllCountriesIntent.RefreshAllCountriesIntent> {
        return swipe_refresh_layout.refreshes().map {
            AllCountriesIntent.RefreshAllCountriesIntent
        }
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

        swipe_refresh_layout.isRefreshing = true
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
        swipe_refresh_layout.isRefreshing = state.isLoading
        emptyView.visible = state.data.isEmpty() && state.isLoading.not()
        recycler_view.visible = state.data.isNotEmpty()
        rvAdapter.updateData(state.data.map {
            countryModelMapper.mapToView(it)
        })

        if (state.error != null) {
            Toast.makeText(context, state.error!!.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun intents(): Observable<AllCountriesIntent> {
        return Observable.merge(
            loadIntent(),
            refreshIntent()
        )
    }


}