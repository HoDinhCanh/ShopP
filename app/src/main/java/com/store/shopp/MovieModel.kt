package com.store.shopp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(var title: String?,var price: String?, var detail: String?, var color: String?, var image: String?):
    Parcelable

