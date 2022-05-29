package com.adedom.myfood.presentation.authentication.state

import com.adedom.data.models.error.BaseError

sealed interface LoginUiState {
    object Initial : LoginUiState

    object EmailSuccess : LoginUiState

    object EmailFailed : LoginUiState

    object PasswordSuccess : LoginUiState

    object PasswordFailed : LoginUiState

    object ShowLoading : LoginUiState

    object HideLoading : LoginUiState

    object LoginSuccess : LoginUiState

    data class LoginError(
        val error: BaseError,
    ) : LoginUiState

    object Register : LoginUiState
}