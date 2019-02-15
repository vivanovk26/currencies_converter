package com.vivanov.currenciesconverter.presentation.main

import android.arch.lifecycle.Lifecycle
import com.vivanov.currenciesconverter.config.di.CURRENCY_RATES_SCOPE
import com.vivanov.currenciesconverter.domain.contracts.ICurrencyRatesContract
import com.vivanov.currenciesconverter.presentation.core.viewmodels.BaseViewModel
import org.koin.core.scope.Scope

class CurrencyRatesViewModel(
    private val currencyRatesInteractor: ICurrencyRatesContract.ICurrencyRatesInteractor
) : BaseViewModel<CurrencyRatesState, CurrencyRatesEvent, CurrencyRatesAction>(),
    ICurrencyRatesContract.ICurrencyRatesViewModel {

    override val scope: Scope = getKoin().getScope(CURRENCY_RATES_SCOPE)
    override val state: CurrencyRatesState = CurrencyRatesState()

    init {
        actionsSubject = currencyRatesInteractor.actionsSubject
    }

    override fun setupLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(currencyRatesInteractor)
    }

    override fun onEventChanged(event: CurrencyRatesEvent) {
        when (event) {

            is CurrencyRatesEvent.ItemClickedEvent -> {
                currencyRatesInteractor.onItemClicked(event.position)
            }

            is CurrencyRatesEvent.AmountChangedEvent -> {
                currencyRatesInteractor.amountChanged(event.amount)
            }
        }
    }

    override fun onActionChanged(action: CurrencyRatesAction) {
        when (action) {

            is CurrencyRatesAction.LoadingAction -> {
                state.loading.value = true
            }

            is CurrencyRatesAction.LoadedAction -> {
                state.loading.value = false
                state.currencyRates.value = action.currencyRates
                state.emptyViewVisible.value = false
            }

            is CurrencyRatesAction.EmptyAction -> {
                state.loading.value = false
                state.currencyRates.value = emptyList()
                state.emptyViewVisible.value = true
            }

            is CurrencyRatesAction.UpdateListAction -> {
                state.updateList.value = true
                state.updateList.value = false
            }

            is CurrencyRatesAction.ErrorAction -> {
                state.loading.value = false
                state.error.value = action.error
                state.error.value = null
            }
        }
    }
}