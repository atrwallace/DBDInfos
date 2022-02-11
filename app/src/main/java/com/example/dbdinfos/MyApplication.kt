package com.example.dbdinfos

import android.app.Application
import com.example.dbdinfos.di.DbDModules
import com.example.dbdinfos.di.networkModule
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(DbDModules, networkModule)
        }
    }

}