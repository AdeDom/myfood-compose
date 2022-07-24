package com.adedom.myfood.presentation.welcome.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.domain.use_cases.welcome.WelcomeGuestRoleUseCase
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.welcome.action.WelcomeUiAction
import com.adedom.myfood.presentation.welcome.state.WelcomeUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val welcomeGuestRoleUseCase: WelcomeGuestRoleUseCase,
) : BaseViewModel<WelcomeUiState, WelcomeUiAction>(WelcomeUiState.Initial) {

    private val _skipChannel = Channel<Unit>()
    val skipChannel = _skipChannel.receiveAsFlow()

    fun skipAction() {
        viewModelScope.launch {
            _skipChannel.send(welcomeGuestRoleUseCase())
        }
    }
}