package com.aliumujib.countryflags.ui.inject

import com.aliumujib.countryflags.ApplicationClass
import com.aliumujib.countryflags.ui.inject.module.ApplicationModule
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
    modules = [AndroidInjectionModule::class, ApplicationModule::class, UIModule::class,
        ImageLoaderModule::class, PresentationModule::class, RemoteModule::class,
        DataModule::class, CacheModule::class]
)
interface ApplicationComponent {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: ApplicationClass): Builder

        fun build(): ApplicationComponent

    }

    fun inject(application: ApplicationClass)

}