package com.adedom.myfood.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<S : Any, A : Any>(initialUiState: S) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    protected val _uiEvent = MutableSharedFlow<A>()
    val uiEvent: SharedFlow<A> = _uiEvent.asSharedFlow()
}