package com.example.weather_forecast_test_repository.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class AppModule : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
    private fun initKoin(app: Application) {
        startKoin {
            androidContext(app)
            listOf<Module>(
                weatherModule
            )
        }
    }
}