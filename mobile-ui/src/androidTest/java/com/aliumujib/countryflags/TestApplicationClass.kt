package com.aliumujib.countryflags

import android.app.Activity
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.aliumujib.countryflags.domain.repositories.countries.ICountriesRepository
import com.aliumujib.countryflags.injection.DaggerTestApplicationComponent
import com.aliumujib.countryflags.injection.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestApplicationClass: Application(), HasActivityInjector {

    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: TestApplicationComponent

    @Inject
    lateinit var countriesRepository: ICountriesRepository

    companion object {
        fun appComponent(): TestApplicationComponent {
            return (ApplicationProvider.getApplicationContext()
                    as TestApplicationClass).appComponent
        }

        fun countriesRepository(): ICountriesRepository {
            return (ApplicationProvider.getApplicationContext()
                    as TestApplicationClass).countriesRepository
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestApplicationComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}