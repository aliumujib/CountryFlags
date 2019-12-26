package com.aliumujib.countryflags.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyModel(
    val code: String,
    val name: String,
    val symbol: String
) : Parcelable