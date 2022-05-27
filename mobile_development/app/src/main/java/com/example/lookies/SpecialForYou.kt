package com.example.lookies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpecialForYou(
    var name: String,
    var description: String,
    var photo: Int
): Parcelable
