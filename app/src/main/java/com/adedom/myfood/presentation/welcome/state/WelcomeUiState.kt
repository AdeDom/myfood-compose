package com.adedom.myfood.presentation.welcome.state

sealed interface WelcomeUiState {
    object Initial : WelcomeUiState
    object Login : WelcomeUiState
    object Register : WelcomeUiState
    object Skip : WelcomeUiState
}