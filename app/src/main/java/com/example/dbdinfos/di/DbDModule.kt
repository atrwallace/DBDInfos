package com.example.dbdinfos.di

import com.example.dbdinfos.data.DbdService
import com.example.dbdinfos.data.MainRepository
import com.example.dbdinfos.data.MainRepositoryImpl
import com.example.dbdinfos.presentation.home.HomeActivity
import com.example.dbdinfos.presentation.home.HomeAdapter
import com.example.dbdinfos.presentation.home.HomeViewModel
import com.example.dbdinfos.presentation.login.MainActivity
import com.example.dbdinfos.presentation.login.MainViewModel
import com.example.dbdinfos.presentation.signup.SignUpActivity
import com.example.dbdinfos.presentation.signup.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val DbDModules = module {
    scope<MainActivity> {
        // Adapter aqui como Scoped
        viewModel { MainViewModel(get()) }
        scoped { createRepo(get()) }
        scoped { provideService(get()) }

    }
    scope<HomeActivity>{
        viewModel { HomeViewModel(get()) }
        scoped { createRepo(get()) }
        scoped { provideService(get()) }
    }
    scope<SignUpActivity>{
        viewModel { SignUpViewModel(get()) }
        scoped { createRepo(get()) }
        scoped { provideService(get()) }
    }
}

fun createRepo(service: DbdService): MainRepository = MainRepositoryImpl(service)

fun provideService(retrofit: Retrofit): DbdService = retrofit.create(DbdService::class.java)
