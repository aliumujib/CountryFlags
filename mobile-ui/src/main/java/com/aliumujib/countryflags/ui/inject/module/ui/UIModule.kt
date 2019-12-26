package com.aliumujib.countryflags.ui.inject.module.ui

import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.ui.AllCountriesActivity
import com.aliumujib.countryflags.ui.inject.module.ui.allcountries.AllCountriesActivityModule
import com.aliumujib.countryflags.ui.inject.module.ui.allcountriesfragment.AllCountriesFragmentProvider
import com.aliumujib.countryflags.ui.utils.threading.PostExecutionThreadImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class UIModule {

    @Binds
    abstract fun bindPostExecutionThread(postExecutionThread: PostExecutionThreadImpl): PostExecutionThread

    @ContributesAndroidInjector(modules = [AllCountriesActivityModule::class, AllCountriesFragmentProvider::class])
    abstract fun contributesMainActivity(): AllCountriesActivity


}