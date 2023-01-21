package com.alexzaitsev.modern

import android.app.Application
import com.alexzaitsev.modern.domain.domainModules
import com.alexzaitsev.modern.view.ViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(domainModules + ViewModule().module)
        }
    }
}