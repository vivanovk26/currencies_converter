package com.vivanov.currenciesconverter.presentation.main

import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.presentation.core.actions.IAction

sealed class CurrencyRatesAction : IAction {

    object LoadingAction : CurrencyRatesAction()

    data class LoadedAction(
        val currencyRates: List<CurrencyRate>
    ) : CurrencyRatesAction()

    object EmptyAction : CurrencyRatesAction()

    data class ErrorAction(
        val error: Throwable
    ) : CurrencyRatesAction()

    object UpdateListAction : CurrencyRatesAction()
}