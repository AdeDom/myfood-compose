package com.adedom.myfood.presentation.splash_screen.state

sealed interface SplashScreenUiState {
    object Initial : SplashScreenUiState
    data class Authentication(
        val isAuth: Boolean
    ) : SplashScreenUiState
}