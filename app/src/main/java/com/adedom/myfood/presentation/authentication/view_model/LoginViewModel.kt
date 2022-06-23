package com.adedom.myfood.presentation.authentication.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.data.utils.ApiServiceException
import com.adedom.data.utils.UseCaseException
import com.adedom.data.utils.toBaseError
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
        viewModelScope.launch {
            try {
                _uiState.update {
                    LoginUiState.ShowLoading
                }

                loginUseCase(email, password)

                _uiState.update {
                    LoginUiState.LoginSuccess
                }
            } catch (ex: UseCaseException) {
                val baseError = ex.toBaseError()
                _uiState.update {
                    LoginUiState.LoginError(baseError)
                }
            } catch (ex: ApiServiceException) {
                val baseError = ex.toBaseError()
                _uiState.update {
                    LoginUiState.LoginError(baseError)
                }
            }
        }
    }
}