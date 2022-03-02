package com.example.dbdinfos.di

import com.example.dbdinfos.dao.MainDAO
import com.example.dbdinfos.data.DbdService
import com.example.dbdinfos.data.MainRepository
import com.example.dbdinfos.data.MainRepositoryImpl
import com.example.dbdinfos.database.MainDTODatabase
import com.example.dbdinfos.presentation.home.HomeActivity
import com.example.dbdinfos.presentation.home.HomeViewModel
import com.example.dbdinfos.presentation.login.MainActivity
import com.example.dbdinfos.presentation.login.MainViewModel
import com.example.dbdinfos.presentation.signup.SignUpActivity
import com.example.dbdinfos.presentation.signup.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val DbDModules = module {
    scope<MainActivity> {
        // Adapter aqui como Scoped
        viewModel { MainViewModel(get()) }
        scoped { createRepo(get(), get()) }
        scoped { provideService(get()) }
        scoped { MainDTODatabase.getInstance(androidContext()).mainDAO }

    }
    scope<HomeActivity> {
        viewModel { HomeViewModel(get()) }
        scoped { createRepo(get(), get()) }
        scoped { provideService(get()) }
        scoped { MainDTODatabase.getInstance(androidContext()).mainDAO }
    }
    scope<SignUpActivity> {
        viewModel { SignUpViewModel(get()) }
        scoped { createRepo(get(), get()) }
        scoped { provideService(get()) }
        scoped { MainDTODatabase.getInstance(androidContext()).mainDAO }
    }
}

fun createRepo(service: DbdService, dao: MainDAO): MainRepository = MainRepositoryImpl(service, dao)

fun provideService(retrofit: Retrofit): DbdService = retrofit.create(DbdService::class.java)
