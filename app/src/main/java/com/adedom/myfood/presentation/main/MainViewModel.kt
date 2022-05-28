package com.adedom.myfood.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adedom.domain.usecases.GetDataUseCase

class MainViewModel(
    private val getDataUseCase: GetDataUseCase,
) : ViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun getData() {
        _data.value = getDataUseCase()
    }
}