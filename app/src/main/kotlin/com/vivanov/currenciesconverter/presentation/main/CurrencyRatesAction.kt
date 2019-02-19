package com.vivanov.currenciesconverter.presentation.main

import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.presentation.core.actions.IAction
import com.vivanov.currenciesconverter.presentation.core.actions.IRefreshableAction

sealed class CurrencyRatesAction : IAction {

    object LoadingAction : CurrencyRatesAction(), IRefreshableAction

    data class LoadedAction(
        val currencyRates: List<CurrencyRate>
    ) : CurrencyRatesAction(), IRefreshableAction

    object EmptyAction : CurrencyRatesAction()

    data class ErrorAction(
        val error: Throwable
    ) : CurrencyRatesAction()

    object UpdateListAction : CurrencyRatesAction()
}