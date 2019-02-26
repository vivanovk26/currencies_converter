package com.vivanov.currenciesconverter.presentation.main

import android.arch.lifecycle.Lifecycle
import com.vivanov.currenciesconverter.config.di.CURRENCY_RATES_SCOPE
import com.vivanov.currenciesconverter.data.providers.IResourcesProvider
import com.vivanov.currenciesconverter.domain.contracts.ICurrencyRatesContract
import com.vivanov.currenciesconverter.domain.format.number.INumberFormatter
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.presentation.core.viewmodels.BaseViewModel
import org.koin.core.scope.Scope
import java.math.BigDecimal

class CurrencyRatesViewModel(
    private val resourcesProvider: IResourcesProvider,
    private val numberFormatter: INumberFormatter,
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

            is CurrencyRatesEvent.RefreshEvent -> {
                currencyRatesInteractor.loadItems()
            }

            is CurrencyRatesEvent.FocusChangedEvent -> {
                currencyRatesInteractor.onFocusChanged(event.position)
            }

            is CurrencyRatesEvent.AmountChangedEvent -> {
                currencyRatesInteractor.onAmountChanged(event.position, BigDecimal(event.amount))
            }

            is CurrencyRatesEvent.ItemClickedEvent -> {
                currencyRatesInteractor.onItemClicked(event.position)
            }
        }
    }

    override fun onActionChanged(action: CurrencyRatesAction) {
        when (action) {

            is CurrencyRatesAction.LoadingAction -> {
                state.loading.value = true
            }

            is CurrencyRatesAction.LoadedListAction -> {
                state.loading.value = false
                state.currencyRateVMs.value = mapToViewData(action.currencyRates)
                state.emptyViewVisible.value = false
            }

            is CurrencyRatesAction.UpdateListAction -> {
                state.currencyRateVMs.value = mapToViewData(action.currencyRates)
            }

            is CurrencyRatesAction.EmptyAction -> {
                state.loading.value = false
                state.currencyRateVMs.value = emptyList()
                state.emptyViewVisible.value = true
            }

            is CurrencyRatesAction.ErrorAction -> {
                state.loading.value = false
                state.error.value = action.error
                state.error.value = null
            }
        }
    }

    private fun mapToViewData(currencyRates: List<CurrencyRate>): List<CurrencyRateVM> {
        return currencyRates.map {
            CurrencyRateVM(
                it.code,
                resourcesProvider.getString(it.description),
                it.iconCode,
                numberFormatter.formatAmount(it.code, it.amount),
                numberFormatter.formatCurrency(it.code)
            )
        }
    }
}