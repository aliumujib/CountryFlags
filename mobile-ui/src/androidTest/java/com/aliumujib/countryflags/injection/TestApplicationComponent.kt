package com.aliumujib.countryflags.injection

import com.aliumujib.countryflags.TestApplicationClass
import com.aliumujib.countryflags.domain.executor.PostExecutionThread
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.ui.inject.module.data.CacheModule
import com.aliumujib.countryflags.ui.inject.module.data.DataModule
import com.aliumujib.countryflags.ui.inject.module.data.RemoteModule
import com.aliumujib.countryflags.ui.inject.module.presentation.PresentationModule
import com.aliumujib.countryflags.ui.inject.module.ui.ImageLoaderModule
import com.aliumujib.countryflags.ui.inject.module.ui.UIModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        TestApplicationModule::class,
        ImageLoaderModule::class,
        PresentationModule::class,
        RemoteModule::class,
        UIModule::class,
        DataModule::class,
        CacheModule::class]
)
interface TestApplicationComponent {

    fun countriesRepository(): ICountriesRepository

    fun postExecutionThread(): PostExecutionThread

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: TestApplicationClass): Builder

        fun build(): TestApplicationComponent

    }

    fun inject(application: TestApplicationClass)

}