package com.adedom.myfood.presentation.splash_screen.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adedom.domain.use_cases.splash_screen.GetIsLoginUseCase
import com.adedom.myfood.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val getIsLoginUseCase: GetIsLoginUseCase,
) : BaseViewModel() {

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    fun getIsLogin() {
        viewModelScope.launch {
            delay(2_000)
            _isLogin.value = getIsLoginUseCase()
        }
    }
}