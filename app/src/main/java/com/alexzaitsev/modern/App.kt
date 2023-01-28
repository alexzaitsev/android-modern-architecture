package com.alexzaitsev.modern

import android.app.Application
import com.alexzaitsev.modern.domain.domainModules
import com.alexzaitsev.modern.view.ViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import timber.log.Timber

class App : Application() {

    private val releaseTree = object : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            logToCrashReportingTool(priority, message, t)
            super.log(priority, tag, message, t)
        }
    }

    private fun logToCrashReportingTool(priority: Int, message: String, t: Throwable?) {
        // TODO log to Crashlytics / Bugsnag / NewRelic / your fav tool
    }

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTimber()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(domainModules + ViewModule().module)
        }
    }

    private fun setupTimber() {
        val tree = if (BuildConfig.DEBUG) {
            Timber.DebugTree()
        } else {
            releaseTree
        }
        Timber.plant(tree)
    }
}
