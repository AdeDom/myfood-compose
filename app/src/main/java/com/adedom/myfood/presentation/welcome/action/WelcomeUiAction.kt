package com.adedom.myfood.presentation.welcome.action

sealed interface WelcomeUiAction {
    object Login : WelcomeUiAction
    object Register : WelcomeUiAction
    data class Skip(val skip: Unit) : WelcomeUiAction
}