package com.aliumujib.countryflags.ui.utils.ext

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal val View.inflater: LayoutInflater get() = LayoutInflater.from(context)

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if(value) View.VISIBLE else View.INVISIBLE
    }


fun RecyclerView.removeAllDecorations() {
    while (this.itemDecorationCount > 0) {
        this.removeItemDecoration(this.getItemDecorationAt(0))
    }
}