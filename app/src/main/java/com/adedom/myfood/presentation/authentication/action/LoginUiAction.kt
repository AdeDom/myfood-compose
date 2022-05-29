package com.adedom.myfood.presentation.authentication.action

sealed interface LoginUiAction {
    object Login : LoginUiAction
    object Register : LoginUiAction
}