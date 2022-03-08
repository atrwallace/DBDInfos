package br.com.dbdinfos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.dbdinfos.dao.MainDAO
import br.com.dbdinfos.data.DataConverter
import br.com.dbdinfos.data.MainDTODBLocal

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