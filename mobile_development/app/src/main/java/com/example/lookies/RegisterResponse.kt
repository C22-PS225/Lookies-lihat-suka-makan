package com.example.lookies

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("msg")
    val message: String
)
