package com.example.lookies.api

import com.example.lookies.PredictKueResponse
import com.example.lookies.login.LoginResponse
import com.example.lookies.register.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("register")
    fun postRegister(
        @Field("nama") nama: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @Multipart
    @POST("predictkue")
    fun uploadImage(
        @Part file: MultipartBody.Part
    ): Call<PredictKueResponse>
}