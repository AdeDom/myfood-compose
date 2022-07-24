package com.adedom.myfood.di

import com.adedom.myfood.presentation.authentication.view_model.LoginViewModel
import com.adedom.myfood.presentation.authentication.view_model.RegisterViewModel
import com.adedom.myfood.presentation.main.view_model.MainViewModel
import com.adedom.myfood.presentation.splash_screen.view_model.SplashScreenViewModel
import com.adedom.myfood.presentation.welcome.view_model.WelcomeViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val presentationModule = DI.Module(name = "presentation") {

    bindProvider { MainViewModel(instance()) }
    bindProvider { SplashScreenViewModel(instance()) }
    bindProvider { WelcomeViewModel(instance()) }
    bindProvider { LoginViewModel(instance(), instance(), instance()) }
    bindProvider { RegisterViewModel() }
}