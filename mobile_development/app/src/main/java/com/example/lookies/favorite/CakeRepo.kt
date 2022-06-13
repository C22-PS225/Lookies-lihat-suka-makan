package com.example.lookies.favorite

import androidx.lifecycle.LiveData
import com.example.lookies.api.Api
import com.example.lookies.app_executors.AppExecutors

class CakeRepo private constructor(
    private val apiService: Api,
    private val CakeDao: CakeDao,
    private val appExecutors: AppExecutors
) {

    fun getFavUser(): LiveData<List<CakeEntity>> = CakeDao.getUsers()

    fun insertCake(cakes: CakeEntity) {
        appExecutors.diskIO.execute {
            CakeDao.insertCake(cakes)
        }
    }

    fun deleteCake(cakes: CakeEntity) {
        appExecutors.diskIO.execute {
            CakeDao.deleteCake(cakes)
        }
    }


    fun isFavorite(name: String?) : LiveData<Boolean> = CakeDao.isFavourite(name)

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