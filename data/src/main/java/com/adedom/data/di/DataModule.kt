package com.adedom.data.di

import com.adedom.data.repositories.DefaultRepository
import com.adedom.data.repositories.DefaultRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val dataModule = DI.Module(name = "data") {

    bindSingleton<DefaultRepository> { DefaultRepositoryImpl() }
}