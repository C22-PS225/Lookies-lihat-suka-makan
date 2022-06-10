package com.example.lookies.camera

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable


data class bitmapImage(
    val img: Bitmap?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Bitmap::class.java.classLoader)
    ) {
    }

    override fun describeContents(): Int {
        return  0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeParcelable(img, flags);
    }

    companion object CREATOR : Parcelable.Creator<bitmapImage> {
        override fun createFromParcel(parcel: Parcel): bitmapImage {
            return bitmapImage(parcel)
        }

        override fun newArray(size: Int): Array<bitmapImage?> {
            return arrayOfNulls(size)
        }
    }
}

