package com.aliumujib.countryflags.ui.utils.imageloader

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import javax.inject.Inject

class GlideImageLoader @Inject constructor(
    private val requestBuilder: RequestBuilder<PictureDrawable>
) : ImageLoader {

    override fun loadImage(url: String, imageView: ImageView) {
        requestBuilder.load(Uri.parse(url)).into(imageView)
    }

}