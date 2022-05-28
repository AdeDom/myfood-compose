package com.adedom.myfood.presentation.main.state

sealed interface MainUiState {
    data class Initial(
        val data: String,
    ) : MainUiState
}