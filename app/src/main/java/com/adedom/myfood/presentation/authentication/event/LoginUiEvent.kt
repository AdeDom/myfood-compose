package com.adedom.myfood.presentation.authentication.event

sealed interface LoginUiEvent {
    object Register : LoginUiEvent
    object LoginSuccess : LoginUiEvent
}