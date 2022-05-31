package com.example.lookies

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("msg")
    val message: String
)