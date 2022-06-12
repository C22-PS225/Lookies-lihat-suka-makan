package com.example.lookies

import com.google.gson.annotations.SerializedName

data class AllCakeResponse(

    @field:SerializedName("kue")
    val kue: ArrayList<KueItem>,

    @field:SerializedName("error")
    val error: String,

    @field:SerializedName("message")
    val message: String
)

data class KueItem(

    @field:SerializedName("nama_kue")
    val namaKue: String,

    @field:SerializedName("gambar")
    val gambar: String,

    @field:SerializedName("paragaf_1")
    val paragaf1: String
)
