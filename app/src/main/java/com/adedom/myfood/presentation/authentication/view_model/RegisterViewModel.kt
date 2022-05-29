package com.adedom.myfood.presentation.authentication.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.authentication.action.RegisterUiAction
import com.adedom.myfood.presentation.authentication.state.RegisterUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
) : BaseViewModel<RegisterUiState, RegisterUiAction>(RegisterUiState.Initial) {

    init {
        uiAction
            .onEach { uiAction ->
                when (uiAction) {
                    RegisterUiAction.Login -> {
                        _uiState.update {
                            RegisterUiState.Login
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun loginAction() {
        viewModelScope.launch {
            val action = RegisterUiAction.Login
            _uiAction.emit(action)
        }
    }
}