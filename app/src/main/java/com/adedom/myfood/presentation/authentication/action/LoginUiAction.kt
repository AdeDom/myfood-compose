package com.adedom.myfood.presentation.authentication.action

sealed interface LoginUiAction {
    data class Email(
        val email: String
    ) : LoginUiAction

    data class Password(
        val password: String
    ) : LoginUiAction

    data class ValidateLoginButton(
        val email: String,
        val password: String,
    ) : LoginUiAction

    data class Login(
        val email: String,
        val password: String,
    ) : LoginUiAction

    object Register : LoginUiAction
}