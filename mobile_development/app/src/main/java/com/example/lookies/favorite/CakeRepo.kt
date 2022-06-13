package com.example.lookies.favorite

import androidx.lifecycle.LiveData
import com.example.lookies.api.Api
import com.example.lookies.app_executors.AppExecutors

class CakeRepo private constructor(
    private val apiService: Api,
    private val CakeDao: CakeDao,
    private val appExecutors: AppExecutors
){

    fun getFavUser(): LiveData<List<CakeEntity>> = CakeDao.getUsers()

    fun insertCake(user: CakeEntity){
        appExecutors.diskIO.execute {
            CakeDao.insertCake(user)
        }
    }

    fun deleteCake(user: CakeEntity){
        appExecutors.diskIO.execute {
            CakeDao.deleteCake(user)
        }
    }

    fun isFavorite(login: String?) : LiveData<Boolean> = CakeDao.isFavourite(login)

    companion object {
        @Volatile
        private var instance: CakeRepo? = null
        fun getInstance(
            apiService: Api,
            newsDao: CakeDao,
            appExecutors: AppExecutors
        ): CakeRepo =
            instance ?: synchronized(this) {
                instance ?: CakeRepo(apiService, newsDao, appExecutors)
            }.also { instance = it }
    }
}