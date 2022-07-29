package com.adedom.myfood.presentation.welcome.event

sealed interface WelcomeUiEvent {
    object Login : WelcomeUiEvent
    object Register : WelcomeUiEvent
    data class Skip(val skip: Unit) : WelcomeUiEvent
}