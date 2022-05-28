package com.adedom.myfood.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adedom.domain.use_cases.get_data.GetDataUseCase
import com.adedom.myfood.base.BaseViewModel

class MainViewModel(
    private val getDataUseCase: GetDataUseCase,
) : BaseViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun getData() {
        _data.value = getDataUseCase()
    }
}