package com.adedom.data.di

import com.adedom.data.providers.remote.ApiServiceInterceptor
import com.adedom.data.providers.remote.DataSourceProvider
import com.adedom.data.providers.remote.auth.AuthRemoteDataSource
import com.adedom.data.providers.remote.auth.AuthRemoteDataSourceImpl
import com.adedom.data.repositories.auth.AuthLoginRepository
import com.adedom.data.repositories.auth.AuthLoginRepositoryImpl
import com.adedom.data.repositories.default_repository.DefaultRepository
import com.adedom.data.repositories.default_repository.DefaultRepositoryImpl
import com.adedom.data.repositories.splash_screen.SplashScreenRepository
import com.adedom.data.repositories.splash_screen.SplashScreenRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val dataModule = DI.Module(name = "data") {

    bindSingleton { ApiServiceInterceptor() }
    bindSingleton { DataSourceProvider(instance()) }

    bindSingleton<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(instance()) }

    bindSingleton<DefaultRepository> { DefaultRepositoryImpl() }
    bindSingleton<SplashScreenRepository> { SplashScreenRepositoryImpl(instance()) }
    bindSingleton<AuthLoginRepository> { AuthLoginRepositoryImpl(instance(), instance()) }
}