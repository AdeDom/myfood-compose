package com.adedom.myfood.presentation.main.event

sealed interface MainUiEvent {
    object Logout : MainUiEvent
}