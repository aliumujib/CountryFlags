package com.aliumujib.countryflags.ui.inject.module.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliumujib.countryflags.presentation.allcountries.AllCountriesViewModel
import com.aliumujib.countryflags.presentation.countrydetails.CountryDetailsViewModel
import com.aliumujib.countryflags.ui.inject.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllCountriesViewModel::class)
    abstract fun bindAllCountriesViewModel(viewModel: AllCountriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryDetailsViewModel::class)
    abstract fun bindCountryDetailsViewModel(
        viewModel: CountryDetailsViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}



@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)