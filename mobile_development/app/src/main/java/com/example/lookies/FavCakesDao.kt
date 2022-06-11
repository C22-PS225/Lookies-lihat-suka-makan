package com.example.lookies

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavCakesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavCakes(favCakes: FavCakes)

    @Query("SELECT * FROM fav_cakes")
    fun getFavCakes(): LiveData<List<FavCakes>>

    @Query("SELECT count(*) FROM fav_cakes WHERE ID_Kue=:idKue")
    fun favCakes(idKue: Int): Int

    @Query("DELETE FROM fav_cakes WHERE ID_Kue=:idKue")
    fun deleteFavCakes(idKue: Int): Int
}