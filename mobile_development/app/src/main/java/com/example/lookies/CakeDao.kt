package com.example.lookies

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CakeDao{
    @Query("SELECT * FROM cakes ")
    fun getUsers(): LiveData<List<CakeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCake(user: CakeEntity)

    @Delete
    fun deleteCake(user: CakeEntity)

    @Query("SELECT EXISTS(SELECT * FROM cakes WHERE name = :name )")
    fun isFavourite(name: String): LiveData<Boolean>
}