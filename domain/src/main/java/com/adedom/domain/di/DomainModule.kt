package com.adedom.domain.di

import com.adedom.domain.usecases.GetDataUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val domainModule = DI.Module(name = "domain") {

    bindProvider { GetDataUseCase(instance()) }
}