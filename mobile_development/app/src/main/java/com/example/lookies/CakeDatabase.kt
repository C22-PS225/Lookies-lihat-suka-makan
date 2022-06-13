package com.example.lookies

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CakeEntity::class], version = 1, exportSchema = false)
abstract class CakeDatabase : RoomDatabase() {
    abstract fun newsDao(): CakeDao

    companion object {
        @Volatile
        private var instance: CakeDatabase? = null
        fun getInstance(context: Context): CakeDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    CakeDatabase::class.java, "FavouriteUser.db"
                ).build()
            }
    }
}