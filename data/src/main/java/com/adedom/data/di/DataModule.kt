package com.adedom.data.di

import com.adedom.data.providers.remote.ApiServiceInterceptor
import com.adedom.data.providers.remote.DataSourceProvider
import com.adedom.data.providers.remote.auth.AuthRemoteDataSource
import com.adedom.data.providers.remote.auth.AuthRemoteDataSourceImpl
import com.adedom.data.providers.remote.category.CategoryRemoteDataSource
import com.adedom.data.providers.remote.category.CategoryRemoteDataSourceImpl
import com.adedom.data.providers.remote.food.FoodRemoteDataSource
import com.adedom.data.providers.remote.food.FoodRemoteDataSourceImpl
import com.adedom.data.repositories.auth.AuthLoginRepository
import com.adedom.data.repositories.auth.AuthLoginRepositoryImpl
import com.adedom.data.repositories.category.CategoryRepository
import com.adedom.data.repositories.category.CategoryRepositoryImpl
import com.adedom.data.repositories.food.FoodRepository
import com.adedom.data.repositories.food.FoodRepositoryImpl
import com.adedom.data.repositories.splash_screen.SplashScreenRepository
import com.adedom.data.repositories.splash_screen.SplashScreenRepositoryImpl
import com.adedom.data.repositories.welcome.WelcomeRepository
import com.adedom.data.repositories.welcome.WelcomeRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val dataModule = DI.Module(name = "data") {

    bindSingleton { ApiServiceInterceptor() }
    bindSingleton { DataSourceProvider(instance()) }

    bindSingleton<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(instance()) }
    bindSingleton<CategoryRemoteDataSource> { CategoryRemoteDataSourceImpl(instance()) }
    bindSingleton<FoodRemoteDataSource> { FoodRemoteDataSourceImpl(instance()) }

    bindSingleton<SplashScreenRepository> { SplashScreenRepositoryImpl(instance()) }
    bindSingleton<AuthLoginRepository> { AuthLoginRepositoryImpl(instance(), instance()) }
    bindSingleton<WelcomeRepository> { WelcomeRepositoryImpl(instance()) }
    bindSingleton<CategoryRepository> { CategoryRepositoryImpl(instance()) }
    bindSingleton<FoodRepository> { FoodRepositoryImpl(instance()) }
}