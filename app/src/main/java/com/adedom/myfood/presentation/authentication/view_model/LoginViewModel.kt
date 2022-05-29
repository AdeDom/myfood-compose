package com.adedom.myfood.presentation.authentication.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.data.repositories.Resource
import com.adedom.domain.use_cases.login.LoginUseCase
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.authentication.action.LoginUiAction
import com.adedom.myfood.presentation.authentication.state.LoginUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginUiState, LoginUiAction>(LoginUiState.Initial) {

    init {
        uiAction
            .onEach { uiAction ->
                when (uiAction) {
                    LoginUiAction.Register -> {
                        _uiState.update {
                            LoginUiState.Register
                        }
                    }
                    LoginUiAction.Login -> {
                        callLogin()
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun loginAction() {
        viewModelScope.launch {
            val action = LoginUiAction.Login
            _uiAction.emit(action)
        }
    }

    fun registerAction() {
        viewModelScope.launch {
            val action = LoginUiAction.Register
            _uiAction.emit(action)
        }
    }

    private fun callLogin() {
        viewModelScope.launch {
            _uiState.update {
                LoginUiState.ShowLoading
            }
            val resource = loginUseCase("dom6", "dom5")
            when (resource) {
                is Resource.Success -> {
                    _uiState.update {
                        LoginUiState.LoginSuccess
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        LoginUiState.LoginError(resource.error)
                    }
                }
            }
            _uiState.update {
                LoginUiState.HideLoading
            }
        }
    }
}