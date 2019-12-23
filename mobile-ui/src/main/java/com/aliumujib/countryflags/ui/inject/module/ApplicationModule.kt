package com.aliumujib.countryflags.ui.inject.module

import android.content.Context
import com.aliumujib.countryflags.ApplicationClass
import com.aliumujib.countryflags.ui.utils.imageloader.GlideImageLoader
import com.aliumujib.countryflags.ui.utils.imageloader.ImageLoader
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {


    @Binds
    abstract fun bindsContext(applicationClass: ApplicationClass): Context



}