package com.adedom.myfood.presentation.splash_screen.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.domain.use_cases.splash_screen.GetIsAuthUseCase
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.splash_screen.action.SplashScreenUiAction
import com.adedom.myfood.presentation.splash_screen.state.SplashScreenUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val getIsAuthUseCase: GetIsAuthUseCase,
) : BaseViewModel<SplashScreenUiState, SplashScreenUiAction>(SplashScreenUiState.Initial) {

    fun getIsAuth() {
        viewModelScope.launch {
            delay(2_000)
            _uiState.update {
                SplashScreenUiState.Authentication(getIsAuthUseCase())
            }
        }
    }
}