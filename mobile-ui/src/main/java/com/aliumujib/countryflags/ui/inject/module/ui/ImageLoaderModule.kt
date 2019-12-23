package com.aliumujib.countryflags.ui.inject.module.ui

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.aliumujib.countryflags.R
import com.aliumujib.countryflags.ui.utils.imageloader.*
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import dagger.Module
import dagger.Provides


@Module
class ImageLoaderModule {

    @Provides
    fun providesImageLoader(imageLoader: GlideImageLoader): ImageLoader {
        return imageLoader
    }

    @Provides
    fun providesRequestBuilder(context: Context): RequestBuilder<PictureDrawable> {
        return GlideApp.with(context)
            .`as`(PictureDrawable::class.java)
            .placeholder(R.drawable.ic_launcher_background)
            .transition(withCrossFade())
            .fitCenter()
            .listener(SVGSoftwareLayerSetter())
    }

}