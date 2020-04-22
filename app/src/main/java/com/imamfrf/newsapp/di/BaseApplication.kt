package com.imamfrf.newsapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //inject Android context
            androidContext(this@BaseApplication)
            // use modules
            modules(listOf(repositoryModule, uiModule, dbModule))
        }
    }
}