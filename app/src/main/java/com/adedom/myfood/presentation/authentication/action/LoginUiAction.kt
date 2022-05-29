package com.adedom.myfood.presentation.authentication.action

sealed interface LoginUiAction {
    object Register : LoginUiAction
}