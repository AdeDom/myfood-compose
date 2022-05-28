package com.adedom.myfood.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<S : Any, A : Any>(initialUiState: S) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    protected val _uiAction = MutableSharedFlow<A>()
    val uiAction: SharedFlow<A> = _uiAction.asSharedFlow()

    protected val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
}