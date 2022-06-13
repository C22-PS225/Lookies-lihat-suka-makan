package com.example.lookies.response

import com.google.gson.annotations.SerializedName

data class AllCakeResponse(

    @field:SerializedName("kue")
    val kue: ArrayList<KueItem3>,

    @field:SerializedName("error")
    val error: String,

    @field:SerializedName("message")
    val message: String
)

data class KueItem3(

    @field:SerializedName("nama_kue")
    val namaKue: String,

    @field:SerializedName("gambar")
    val gambar: String,

    @field:SerializedName("paragaf_1")
    val paragaf1: String
)
