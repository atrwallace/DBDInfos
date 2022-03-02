package com.example.dbdinfos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dbdinfos.dao.MainDAO
import com.example.dbdinfos.data.DataConverter
import com.example.dbdinfos.data.MainDTODBLocal

@Database(entities = [MainDTODBLocal::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MainDTODatabase : RoomDatabase() {

    abstract val mainDAO: MainDAO

    companion object {

        @Volatile
        private var INSTANCE: MainDTODatabase? = null
        fun getInstance(context: Context): MainDTODatabase {
            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, MainDTODatabase::class.java, "maindb"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}