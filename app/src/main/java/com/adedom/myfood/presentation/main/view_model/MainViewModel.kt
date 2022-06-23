package com.adedom.myfood.presentation.main.view_model

import androidx.lifecycle.viewModelScope
import com.adedom.domain.use_cases.main.MainPageUseCase
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.main.action.MainUiAction
import com.adedom.myfood.presentation.main.state.MainUiState
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainPageUseCase: MainPageUseCase,
) : BaseViewModel<MainUiState, MainUiAction>(MainUiState.Initial) {

    fun callApiService() {
        viewModelScope.launch {
            try {
                mainPageUseCase()
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
        }
    }
}