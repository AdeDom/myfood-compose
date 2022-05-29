package com.adedom.myfood.presentation.authentication.action

sealed interface LoginUiAction {
    data class Email(
        val email: String
    ) : LoginUiAction

    data class Password(
        val password: String
    ) : LoginUiAction

    object Login : LoginUiAction

    object Register : LoginUiAction
}