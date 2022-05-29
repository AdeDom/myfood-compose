package com.adedom.myfood.presentation.authentication.state

sealed interface LoginUiState {
    object Initial : LoginUiState
    object Register : LoginUiState
}