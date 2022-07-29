package com.adedom.myfood.presentation.welcome.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.domain.use_cases.welcome.WelcomeGuestRoleUseCase
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.welcome.action.WelcomeUiAction
import com.adedom.myfood.presentation.welcome.state.WelcomeUiState
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val welcomeGuestRoleUseCase: WelcomeGuestRoleUseCase,
) : BaseViewModel<WelcomeUiState, WelcomeUiAction>(WelcomeUiState.Initial) {

    fun onLoginEvent() {
        viewModelScope.launch {
            val event = WelcomeUiAction.Login
            _uiAction.emit(event)
        }
    }

    fun onRegisterEvent() {
        viewModelScope.launch {
            val event = WelcomeUiAction.Register
            _uiAction.emit(event)
        }
    }

    fun onSkipEvent() {
        viewModelScope.launch {
            val event = WelcomeUiAction.Skip(welcomeGuestRoleUseCase())
            _uiAction.emit(event)
        }
    }
}