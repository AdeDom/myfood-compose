package com.adedom.myfood.presentation.authentication.event

sealed interface RegisterUiEvent {
    object Login : RegisterUiEvent
}