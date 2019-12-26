package com.aliumujib.countryflags.ui.inject.module.ui.allcountriesfragment

import com.aliumujib.countryflags.models.CountryModel
import com.aliumujib.countryflags.navigator.Navigator
import com.aliumujib.countryflags.navigator.NavigatorImpl
import com.aliumujib.countryflags.ui.allcountries.AllCountriesFragment
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Singleton

@Module
class AllCountriesFragmentModule {

    @Provides
    fun providesPublishSubject(): PublishSubject<CountryModel> {
        return PublishSubject.create()
    }


}