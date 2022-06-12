package com.example.lookies

import com.google.gson.annotations.SerializedName

data class Cakes(

    @field:SerializedName("nama_kue")
    val namaKue: String,

    @field:SerializedName("bahan")
    val bahan: String,

    @field:SerializedName("paragaf_1")
    val paragaf1: String,

    @field:SerializedName("paragaf_2")
    val paragaf2: String,

    @field:SerializedName("ID_Kue")
    val iDKue: Int,

    @field:SerializedName("gambar")
    val gambar: String,

    @field:SerializedName("hasil_ML")
    val hasilML: String
)
