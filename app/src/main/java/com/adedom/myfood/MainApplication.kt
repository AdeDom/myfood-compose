package com.adedom.myfood

import android.app.Application
import com.adedom.data.di.dataModule
import com.adedom.domain.di.domainModule
import com.adedom.myfood.di.presentationModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class MainApplication : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXModule(this@MainApplication))

        importAll(
            dataModule,
            domainModule,
            presentationModule,
        )
    }
}