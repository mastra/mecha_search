package com.molol.mechasearch.util

import android.app.Application
import com.molol.mechasearch.data.di.dataModule
import com.molol.mechasearch.ui.di.appModule
import org.koin.core.context.startKoin

class MeChaSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            //androidLogger()
            //inject Android context
            //androidContext(this@MainApplication)
            // use modules
            modules(appModule, dataModule)
        }

    }
}