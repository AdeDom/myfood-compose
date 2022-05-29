package com.adedom.myfood.presentation.authentication.state

sealed interface RegisterUiState {
    object Initial : RegisterUiState
    object Login : RegisterUiState
}