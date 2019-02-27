package com.vivanov.currenciesconverter.config.di

import org.koin.dsl.module.Module

object KoinModules {

    fun getAllModules(): List<Module> {
        return listOf(
            appModule,
            formatModule,
            currencyRatesModule,
            networkModule
        )
    }
}