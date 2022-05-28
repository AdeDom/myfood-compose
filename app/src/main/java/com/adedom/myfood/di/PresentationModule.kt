package com.adedom.myfood.di

import com.adedom.myfood.presentation.main.MainViewModel
import com.adedom.myfood.presentation.splash_screen.view_model.SplashScreenViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val presentationModule = DI.Module(name = "presentation") {

    bindProvider { MainViewModel(instance()) }
    bindProvider { SplashScreenViewModel(instance()) }
}