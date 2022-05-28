package com.adedom.myfood.base

import androidx.fragment.app.Fragment
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI

abstract class BaseFragment : Fragment(), DIAware {

    override val di: DI by closestDI()
}