package com.adedom.myfood.di

import com.adedom.myfood.presentation.main.MainViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val presentationModule = DI.Module(name = "presentation") {

    bindProvider { MainViewModel(instance()) }
}