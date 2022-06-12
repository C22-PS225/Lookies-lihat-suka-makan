package com.example.lookies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchRes(
    var name: String,
    var description: String,
    var photo: String
): Parcelable