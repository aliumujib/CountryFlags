package com.aliumujib.countryflags.ui.utils.ext

import android.os.Handler


fun delay(time: Long = 1000, function: () -> Unit) {
    val handler = Handler()
    handler.postDelayed({
        function.invoke()
    }, time)
}