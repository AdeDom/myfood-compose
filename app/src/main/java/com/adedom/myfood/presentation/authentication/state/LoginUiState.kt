package com.adedom.myfood.presentation.authentication.state

sealed interface LoginUiState {
    object Initial : LoginUiState

    object ShowLoading : LoginUiState

    object HideLoading : LoginUiState

    object LoginSuccess : LoginUiState

    data class LoginError(
        val error: String,
    ) : LoginUiState

    object Register : LoginUiState
}