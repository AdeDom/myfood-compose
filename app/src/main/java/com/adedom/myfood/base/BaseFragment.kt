package com.adedom.myfood.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI

abstract class BaseFragment : Fragment(), DIAware {

    override val di: DI by closestDI()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUiState()
        setupInitial()
        setupUiEvent()
    }

    open fun setupUiState() {}

    open fun setupInitial() {}

    open fun setupUiEvent() {}
}