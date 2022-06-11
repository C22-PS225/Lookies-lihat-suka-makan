package com.example.lookies

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "fav_cakes")
@Parcelize
data class FavCakes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_Kue")
    var idKue: Int,

    @ColumnInfo(name = "gambar")
    var gambar: String,

    @ColumnInfo(name = "nama_kue")
    var namaKue: String,

    @ColumnInfo(name = "paragraf_1")
    var paragraf1: String

) : Parcelable
