package com.adedom.domain.di

import com.adedom.domain.use_cases.get_data.GetDataUseCase
import com.adedom.domain.use_cases.login.LoginUseCase
import com.adedom.domain.use_cases.splash_screen.GetIsAuthUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val domainModule = DI.Module(name = "domain") {

    bindProvider { GetDataUseCase(instance()) }
    bindProvider { GetIsAuthUseCase(instance()) }
    bindProvider { LoginUseCase(instance()) }
}