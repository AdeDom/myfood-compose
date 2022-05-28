package com.adedom.myfood.presentation.main.view_model

import com.adedom.domain.use_cases.get_data.GetDataUseCase
import com.adedom.myfood.base.BaseViewModel
import com.adedom.myfood.presentation.main.action.MainUiAction
import com.adedom.myfood.presentation.main.state.MainUiState

class MainViewModel(
    private val getDataUseCase: GetDataUseCase,
) : BaseViewModel<MainUiState, MainUiAction>(MainUiState.Initial(getDataUseCase()))