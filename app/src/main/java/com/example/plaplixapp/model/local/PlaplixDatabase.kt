package com.example.plaplixapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plaplixapp.model.local.dao.PlaplixDao
import com.example.plaplixapp.model.local.dao.PlaplixDetailsDao
import com.example.plaplixapp.model.local.entities.PlapixEnti
import com.example.plaplixapp.model.local.entities.PlapixdetallEnti

@Database(entities = [PlapixEnti::class, PlapixdetallEnti::class], version = 1)
abstract class PlaplixRoomDatabase : RoomDatabase(){

    abstract fun getCellDao(): PlaplixDao
    abstract fun getDetailsDao(): PlaplixDetailsDao

    companion object{
        @Volatile
        private var INSTANCE: PlaplixRoomDatabase? = null

        fun getDatabase(contex: Context): PlaplixRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    contex.applicationContext,
                    PlaplixRoomDatabase::class.java,
                    "plaplix_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}