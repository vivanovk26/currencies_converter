package com.vivanov.currenciesconverter.domain.contracts

import android.arch.lifecycle.LifecycleObserver
import com.vivanov.currenciesconverter.domain.interactors.core.IBaseInteractor
import com.vivanov.currenciesconverter.presentation.core.viewmodels.IBaseViewModel
import com.vivanov.currenciesconverter.presentation.core.views.IBaseView
import com.vivanov.currenciesconverter.presentation.main.CurrencyRatesAction
import com.vivanov.currenciesconverter.presentation.main.CurrencyRatesEvent
import com.vivanov.currenciesconverter.presentation.main.CurrencyRatesState
import java.math.BigDecimal

interface ICurrencyRatesContract {

    interface ICurrencyRatesView : IBaseView {

        fun onItemClicked(position: Int)

        fun onItemFocused(position: Int)

        fun onAmountChanged(position: Int, amountText: String)
    }

    interface ICurrencyRatesViewModel :
        IBaseViewModel<CurrencyRatesState, CurrencyRatesEvent, CurrencyRatesAction>

    interface ICurrencyRatesInteractor : IBaseInteractor<CurrencyRatesAction>, LifecycleObserver {

        fun loadItems()

        fun onFocusChanged(position: Int)

        fun onAmountChanged(position: Int, amount: BigDecimal)

        fun onItemClicked(position: Int)
    }
}