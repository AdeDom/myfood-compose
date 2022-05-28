package com.adedom.data.di

import com.adedom.data.repositories.default_repository.DefaultRepository
import com.adedom.data.repositories.default_repository.DefaultRepositoryImpl
import com.adedom.data.repositories.splash_screen.SplashScreenRepository
import com.adedom.data.repositories.splash_screen.SplashScreenRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val dataModule = DI.Module(name = "data") {

    bindSingleton<DefaultRepository> { DefaultRepositoryImpl() }
    bindSingleton<SplashScreenRepository> { SplashScreenRepositoryImpl() }
}