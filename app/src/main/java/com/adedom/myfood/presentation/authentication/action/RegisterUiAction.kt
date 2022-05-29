package com.adedom.myfood.presentation.authentication.action

sealed interface RegisterUiAction {
    object Login : RegisterUiAction
}