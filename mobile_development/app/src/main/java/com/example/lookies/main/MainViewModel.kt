//package com.example.lookies.main
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.lookies.response.Cakes
//import com.example.lookies.favorite.FavCakes
//import com.example.lookies.favorite.FavCakesDao
//import com.example.lookies.favorite.FavCakesDatabase
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class MainViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val _listCakes = MutableLiveData<Cakes>()
//    val listCakes: LiveData<Cakes> = _listCakes
//
//    private var favCakesDao: FavCakesDao
//    private var favCakesDb: FavCakesDatabase = FavCakesDatabase.getDatabase(application)
//
//    init {
//        favCakesDao = favCakesDb.favCakesDao()
//    }
//
//    fun addFavCakes(idKue: Int, gambar: String, namaKue: String, paragraf1: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val favCakes = FavCakes(
//                idKue, gambar, namaKue, paragraf1
//            )
//            favCakesDao.addFavCakes(favCakes)
//        }
//    }
//
//    fun favCakes(idKue: Int) = favCakesDao.favCakes(idKue)
//
//    fun deleteFavCakes(idKue: Int) {
//        CoroutineScope(Dispatchers.IO).launch {
//            favCakesDao.deleteFavCakes(idKue)
//        }
//    }
//
//    fun getFavCakes(): LiveData<List<FavCakes>> {
//        return favCakesDao.getFavCakes()
//    }
//
//}