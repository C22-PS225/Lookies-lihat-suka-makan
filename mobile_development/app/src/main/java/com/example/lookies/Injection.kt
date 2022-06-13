package com.example.lookies

import android.content.Context
import com.example.lookies.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): CakeRepo {
        val apiService = ApiConfig.getApi()
        val database = CakeDatabase.getInstance(context)
        val dao = database.newsDao()
        val appExecutors = AppExecutors()
        return CakeRepo.getInstance(apiService, dao, appExecutors)
    }
}