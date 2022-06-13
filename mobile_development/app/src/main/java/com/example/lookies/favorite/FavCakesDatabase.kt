//package com.example.lookies.favorite
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [FavCakes::class], version = 1, exportSchema = false)
//abstract class FavCakesDatabase : RoomDatabase() {
//    abstract fun favCakesDao(): FavCakesDao
//
//    companion object {
//        @Volatile
//        private var instance: FavCakesDatabase? = null
//
//        @JvmStatic
//        fun getDatabase(context: Context): FavCakesDatabase {
//            if (instance == null) {
//                synchronized(FavCakesDatabase::class.java) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        FavCakesDatabase::class.java, "favCakes_database"
//                    )
//                        .build()
//                }
//            }
//            return instance as FavCakesDatabase
//        }
//    }
//}