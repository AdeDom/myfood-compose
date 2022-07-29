package com.adedom.myfood.presentation.authentication.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.authentication.event.RegisterUiEvent
import com.adedom.myfood.presentation.authentication.state.RegisterUiState
import kotlinx.coroutines.launch

class RegisterViewModel(
) : BaseViewModel<RegisterUiState, RegisterUiEvent>(RegisterUiState.Initial) {

    fun onLoginEvent() {
        viewModelScope.launch {
            val event = RegisterUiEvent.Login
            _uiEvent.emit(event)
        }
    }
}