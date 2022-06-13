package com.example.lookies.favorite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CakeDao {
    @Query("SELECT * FROM cakes ")
    fun getUsers(): LiveData<List<CakeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCake(cakes: CakeEntity)

    @Delete
    fun deleteCake(cakes: CakeEntity)

    @Query("SELECT EXISTS(SELECT * FROM cakes WHERE name = :name )")
    fun isFavourite(name: String?): LiveData<Boolean>
}