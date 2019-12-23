package com.aliumujib.countryflags.ui.utils.ext

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Int): Int {
    var displayMetrics = resources.displayMetrics
    return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}