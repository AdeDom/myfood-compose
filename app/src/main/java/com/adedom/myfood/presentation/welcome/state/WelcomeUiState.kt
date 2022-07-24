package com.adedom.myfood.presentation.welcome.state

sealed interface WelcomeUiState {
    object Initial : WelcomeUiState
    object Skip : WelcomeUiState
}