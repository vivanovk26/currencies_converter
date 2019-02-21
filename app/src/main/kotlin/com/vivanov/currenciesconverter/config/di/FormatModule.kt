package com.vivanov.currenciesconverter.config.di

import android.annotation.SuppressLint
import com.vivanov.currenciesconverter.domain.format.date.DateFormatter
import com.vivanov.currenciesconverter.domain.format.date.IDateFormatter
import com.vivanov.currenciesconverter.domain.format.number.INumberFormatter
import com.vivanov.currenciesconverter.domain.format.number.NumberFormatter
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
    single<INumberFormatter> {
        NumberFormatter()
    }
}