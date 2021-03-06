package com.vivanov.currenciesconverter.config.di

import android.annotation.SuppressLint
import com.vivanov.currenciesconverter.domain.format.number.INumberFormatter
import com.vivanov.currenciesconverter.domain.format.number.NumberFormatter
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import java.util.*

@SuppressLint("ConstantLocale")
val formatModule: Module = module {

    // Restrict this version by one locale
    factory<Locale> {
        Locale.UK
    }
    single<INumberFormatter> {
        NumberFormatter(get(), get())
    }
}