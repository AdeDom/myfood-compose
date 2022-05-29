package com.adedom.myfood.presentation.authentication.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.authentication.action.LoginUiAction
import com.adedom.myfood.presentation.authentication.state.LoginUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel<LoginUiState, LoginUiAction>(LoginUiState.Initial) {

    init {
        uiAction
            .onEach { uiAction ->
                when (uiAction) {
                    LoginUiAction.Register -> {
                        _uiState.update {
                            LoginUiState.Register
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun registerAction() {
        viewModelScope.launch {
            val action = LoginUiAction.Register
            _uiAction.emit(action)
        }
    }
}