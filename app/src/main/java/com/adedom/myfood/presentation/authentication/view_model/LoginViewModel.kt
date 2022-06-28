package com.adedom.myfood.presentation.authentication.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.data.utils.ApiServiceException
import com.adedom.data.utils.UseCaseException
import com.adedom.domain.use_cases.login.LoginUseCase
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.authentication.action.LoginUiAction
import com.adedom.myfood.presentation.authentication.state.LoginUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginUiState, LoginUiAction>(LoginUiState.Initial) {

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        error.printStackTrace()

        when (error) {
            is ApiServiceException -> {
                _uiState.update {
                    LoginUiState.LoginError(error.toBaseError())
                }
            }
            is UseCaseException -> {
                _uiState.update {
                    LoginUiState.LoginError(error.toBaseError())
                }
            }
        }
    }

    init {
        uiAction
            .onEach { uiAction ->
                when (uiAction) {
                    is LoginUiAction.Email -> {
                        val isValidateEmail = loginUseCase.isValidateEmail(uiAction.email)
                        _uiState.update {
                            if (isValidateEmail) LoginUiState.EmailSuccess else LoginUiState.EmailFailed
                        }
                    }
                    is LoginUiAction.Password -> {
                        val isValidatePassword = loginUseCase.isValidatePassword(uiAction.password)
                        _uiState.update {
                            if (isValidatePassword) LoginUiState.PasswordSuccess else LoginUiState.PasswordFailed
                        }
                    }
                    is LoginUiAction.ValidateLoginButton -> {
                        val isValidateEmail = loginUseCase.isValidateEmail(uiAction.email)
                        val isValidatePassword = loginUseCase.isValidatePassword(uiAction.password)
                        if (isValidateEmail && isValidatePassword) {
                            _uiState.update {
                                LoginUiState.ValidateLoginButton
                            }
                        }
                    }
                    LoginUiAction.Register -> {
                        _uiState.update {
                            LoginUiState.Register
                        }
                    }
                    is LoginUiAction.Login -> {
                        callLogin(uiAction.email, uiAction.password)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun emailAction(email: String) {
        viewModelScope.launch {
            val action = LoginUiAction.Email(email)
            _uiAction.emit(action)
        }
    }

    fun passwordAction(password: String) {
        viewModelScope.launch {
            val action = LoginUiAction.Password(password)
            _uiAction.emit(action)
        }
    }

    fun validateLoginButtonAction(email: String, password: String) {
        viewModelScope.launch {
            val action = LoginUiAction.ValidateLoginButton(email, password)
            _uiAction.emit(action)
        }
    }

    fun loginAction(email: String, password: String) {
        viewModelScope.launch {
            val action = LoginUiAction.Login(email, password)
            _uiAction.emit(action)
        }
    }

    fun registerAction() {
        viewModelScope.launch {
            val action = LoginUiAction.Register
            _uiAction.emit(action)
        }
    }

    private fun callLogin(email: String, password: String) {
        viewModelScope.launch(exceptionHandler) {
            _uiState.update {
                LoginUiState.ShowLoading
            }

            loginUseCase(email, password)

            _uiState.update {
                LoginUiState.LoginSuccess
            }
        }
    }
}