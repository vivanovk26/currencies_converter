package com.vivanov.currenciesconverter.config.app

import android.app.Application
import com.vivanov.currenciesconverter.BuildConfig
import com.vivanov.currenciesconverter.config.di.KoinModules
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger
import org.koin.log.EmptyLogger
import org.koin.log.Logger

class CurrenciesConverterApp : Application() {

    private val appConfig: IAppConfig by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin(this, KoinModules.getAllModules(), logger = getLogger())
        appConfig.initAppConfig()
    }

    private fun getLogger(): Logger {
        return if (BuildConfig.DEBUG) {
            AndroidLogger()
        } else {
            EmptyLogger()
        }
    }
}