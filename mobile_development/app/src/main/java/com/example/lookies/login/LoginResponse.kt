package com.example.lookies.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("msg")
    val message: String,

    @field:SerializedName("token")
    val token: String
)