package com.adedom.myfood.presentation.main.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.domain.use_cases.logout.LogoutUseCase
import com.adedom.domain.use_cases.main.MainPageUseCase
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.main.event.MainUiEvent
import com.adedom.myfood.presentation.main.state.MainUiState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainPageUseCase: MainPageUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel<MainUiState, MainUiEvent>(MainUiState.Initial) {

    fun callApiService() {
        viewModelScope.launch {
            try {
                mainPageUseCase()
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
        }
    }

    fun callLogout() {
        GlobalScope.launch {
            logoutUseCase()
        }
    }

    fun onLogoutEvent() {
        viewModelScope.launch {
            val event = MainUiEvent.Logout
            _uiEvent.emit(event)
        }
    }
}