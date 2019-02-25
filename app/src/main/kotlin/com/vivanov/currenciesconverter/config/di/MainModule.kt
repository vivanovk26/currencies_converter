package com.vivanov.currenciesconverter.config.di

import com.vivanov.currenciesconverter.data.repositories.CurrencyRatesRepository
import com.vivanov.currenciesconverter.data.repositories.ICurrencyRatesRepository
import com.vivanov.currenciesconverter.domain.contracts.ICurrencyRatesContract
import com.vivanov.currenciesconverter.domain.interactors.main.CurrencyRatesInteractor
import com.vivanov.currenciesconverter.presentation.main.CurrencyRatesAdapter
import com.vivanov.currenciesconverter.presentation.main.CurrencyRatesViewModel
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

const val CURRENCY_RATES_SCOPE: String = "CURRENCY_RATES_SCOPE"

val currencyRatesModule: Module = module {

    scope(CURRENCY_RATES_SCOPE) {
        CurrencyRatesAdapter(get())
    }
    viewModel<CurrencyRatesViewModel>()
    scope<ICurrencyRatesContract.ICurrencyRatesViewModel>(CURRENCY_RATES_SCOPE) {
        get<CurrencyRatesViewModel>()
    }
    scope<ICurrencyRatesContract.ICurrencyRatesInteractor>(CURRENCY_RATES_SCOPE) {
        CurrencyRatesInteractor(get(), get())
    }
    scope<ICurrencyRatesRepository>(CURRENCY_RATES_SCOPE) {
        CurrencyRatesRepository(get())
    }
}