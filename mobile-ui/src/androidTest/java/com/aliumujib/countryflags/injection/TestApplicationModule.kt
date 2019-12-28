package com.aliumujib.countryflags.injection

import android.content.Context
import com.aliumujib.countryflags.TestApplicationClass
import dagger.Binds
import dagger.Module

@Module
abstract class TestApplicationModule {

    @Binds
    abstract fun bindsContext(applicationClass: TestApplicationClass): Context

}