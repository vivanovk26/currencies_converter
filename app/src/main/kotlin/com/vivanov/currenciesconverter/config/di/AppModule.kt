package com.vivanov.currenciesconverter.config.di

import com.vivanov.currenciesconverter.config.app.AppConfig
import com.vivanov.currenciesconverter.config.app.IAppConfig
import com.vivanov.currenciesconverter.data.providers.IResourcesProvider
import com.vivanov.currenciesconverter.data.providers.ResourcesProvider
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModule: Module = module {

    single<IAppConfig> {
        AppConfig(get(), get())
    }

    single<IResourcesProvider> {
        ResourcesProvider(get())
    }
}