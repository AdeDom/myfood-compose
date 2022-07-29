package com.adedom.myfood.presentation.authentication.action

sealed interface RegisterUiEvent {
    object Login : RegisterUiEvent
}