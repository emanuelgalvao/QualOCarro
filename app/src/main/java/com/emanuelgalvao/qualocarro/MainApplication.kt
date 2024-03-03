package com.emanuelgalvao.qualocarro

import android.app.Application
import com.emanuelgalvao.qualocarro.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}