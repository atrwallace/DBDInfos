package br.com.dbdinfos

import android.app.Application
import br.com.dbdinfos.di.DbDModules
import br.com.dbdinfos.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(DbDModules, networkModule)
        }
    }

}