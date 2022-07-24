package com.adedom.myfood.presentation.welcome.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.domain.use_cases.welcome.WelcomeGuestRoleUseCase
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.welcome.action.WelcomeUiAction
import com.adedom.myfood.presentation.welcome.state.WelcomeUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val welcomeGuestRoleUseCase: WelcomeGuestRoleUseCase,
) : BaseViewModel<WelcomeUiState, WelcomeUiAction>(WelcomeUiState.Initial) {

    init {
        uiAction
            .onEach { uiAction ->
                when (uiAction) {
                    WelcomeUiAction.Skip -> {
                        welcomeGuestRoleUseCase()
                        _uiState.update {
                            WelcomeUiState.Skip
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun skipAction() {
        viewModelScope.launch {
            val action = WelcomeUiAction.Skip
            _uiAction.emit(action)
        }
    }
}