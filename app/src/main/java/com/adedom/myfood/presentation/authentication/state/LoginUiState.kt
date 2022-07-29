package com.adedom.myfood.presentation.authentication.state

import com.adedom.data.models.error.BaseError

sealed interface LoginUiState {
    object Initial : LoginUiState

    data class Email(
        val isError: Boolean,
        val isLogin: Boolean,
    ) : LoginUiState

    data class Password(
        val isError: Boolean,
        val isLogin: Boolean,
    ) : LoginUiState

    data class Loading(
        val isLoading: Boolean,
        val isLogin: Boolean,
    ) : LoginUiState

    data class LoginError(
        val error: BaseError,
    ) : LoginUiState

    data class LoginForm(
        val email: String = "",
        val password: String = "",
    )
}