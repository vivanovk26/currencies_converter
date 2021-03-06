package com.vivanov.currenciesconverter.presentation.main

import android.arch.lifecycle.MutableLiveData
import com.vivanov.currenciesconverter.presentation.core.states.IState

class CurrencyRatesState(
    val loading: MutableLiveData<Boolean> = MutableLiveData(),
    val currencyRateVMs: MutableLiveData<List<CurrencyRateVM>> = MutableLiveData(),
    val emptyViewVisible: MutableLiveData<Boolean> = MutableLiveData(),
    val error: MutableLiveData<Throwable?> = MutableLiveData()
) : IState