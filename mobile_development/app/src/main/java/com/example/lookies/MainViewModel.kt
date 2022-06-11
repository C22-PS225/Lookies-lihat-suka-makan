package com.example.lookies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var favCakesDao: FavCakesDao
    private var favCakesDb: FavCakesDatabase = FavCakesDatabase.getDatabase(application)

    init {
        favCakesDao = favCakesDb.favCakesDao()
    }

    fun addFavCakes(idKue: Int, gambar: String, namaKue: String, paragraf1: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val favCakes = FavCakes(
                idKue, gambar, namaKue, paragraf1
            )
            favCakesDao.addFavCakes(favCakes)
        }
    }

    fun favCakes(idKue: Int) = favCakesDao.favCakes(idKue)

    fun deleteFavCakes(idKue: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            favCakesDao.deleteFavCakes(idKue)
        }
    }

    fun getFavCakes(): LiveData<List<FavCakes>> {
        return favCakesDao.getFavCakes()
    }
}