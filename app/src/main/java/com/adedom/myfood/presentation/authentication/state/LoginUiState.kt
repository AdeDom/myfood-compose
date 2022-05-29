package com.adedom.myfood.presentation.authentication.state

sealed interface LoginUiState {
    object Initial : LoginUiState

    data class Loading(
        val isLoading: Boolean,
    ) : LoginUiState

    object LoginSuccess : LoginUiState

    data class LoginError(
        val error: String,
    ) : LoginUiState

    object Register : LoginUiState
}