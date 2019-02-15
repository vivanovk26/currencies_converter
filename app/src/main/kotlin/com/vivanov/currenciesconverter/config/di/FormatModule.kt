package com.vivanov.currenciesconverter.config.di

import android.annotation.SuppressLint
import com.vivanov.currenciesconverter.domain.format.date.DateFormatter
import com.vivanov.currenciesconverter.domain.format.date.IDateFormatter
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import java.util.*

@SuppressLint("ConstantLocale")
val formatModule: Module = module {

    factory<Locale> {
        Locale.getDefault()
    }
    single<IDateFormatter> {
        DateFormatter(get())
    }
}