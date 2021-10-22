package com.example.newyorktimes.app

import android.app.Application
import android.content.Context
import com.example.newyorktimes.di.module.repoModule
import com.example.newyorktimes.di.module.viewModelModule
import com.example.newyorktimes.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewYorkTimesApplication: Application() {

    companion object {
        private lateinit var appContext: Context

        @JvmStatic
        fun getContext(): Context {
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewYorkTimesApplication)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}