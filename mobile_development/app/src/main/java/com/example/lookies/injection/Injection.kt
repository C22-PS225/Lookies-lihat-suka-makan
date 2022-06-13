package com.example.lookies.injection

import android.content.Context
import com.example.lookies.api.ApiConfig
import com.example.lookies.app_executors.AppExecutors
import com.example.lookies.favorite.CakeDatabase
import com.example.lookies.favorite.CakeRepo

object Injection {
    fun provideRepository(context: Context): CakeRepo {
        val apiService = ApiConfig.getApi()
        val database = CakeDatabase.getInstance(context)
        val dao = database.newsDao()
        val appExecutors = AppExecutors()
        return CakeRepo.getInstance(apiService, dao, appExecutors)
    }
}