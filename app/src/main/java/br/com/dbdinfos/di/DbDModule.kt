package br.com.dbdinfos.di

import br.com.dbdinfos.dao.MainDAO
import br.com.dbdinfos.data.DbdService
import br.com.dbdinfos.data.MainRepository
import br.com.dbdinfos.data.MainRepositoryImpl
import br.com.dbdinfos.database.MainDTODatabase
import br.com.dbdinfos.presentation.home.HomeActivity
import br.com.dbdinfos.presentation.home.HomeViewModel
import br.com.dbdinfos.presentation.login.LoginActivity
import br.com.dbdinfos.presentation.login.LoginViewModel
import br.com.dbdinfos.presentation.signup.SignUpActivity
import br.com.dbdinfos.presentation.signup.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val DbDModules = module {
    scope<LoginActivity> {
        // Adapter aqui como Scoped
        viewModel { LoginViewModel(get()) }
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
