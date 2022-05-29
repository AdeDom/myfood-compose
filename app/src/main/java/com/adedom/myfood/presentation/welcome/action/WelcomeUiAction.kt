package com.adedom.myfood.presentation.welcome.action

sealed interface WelcomeUiAction {
    object Initial : WelcomeUiAction
    object Login : WelcomeUiAction
    object Register : WelcomeUiAction
    object Skip : WelcomeUiAction
}