package com.vivanov.currenciesconverter.domain.interactors.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.vivanov.currenciesconverter.data.error.handlers.IErrorHandler
import com.vivanov.currenciesconverter.data.repositories.ICurrencyRatesRepository
import com.vivanov.currenciesconverter.domain.contracts.ICurrencyRatesContract
import com.vivanov.currenciesconverter.domain.interactors.core.BaseInteractor
import com.vivanov.currenciesconverter.domain.model.Currency
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.extensions.IRxSchedulers
import com.vivanov.currenciesconverter.extensions.mainThread
import com.vivanov.currenciesconverter.presentation.main.CurrencyRatesAction
import java.math.BigDecimal

class CurrencyRatesInteractor(
    private val currencyRatesRepository: ICurrencyRatesRepository,
    private val errorHandler: IErrorHandler,
    private val rxSchedulers: IRxSchedulers
) : BaseInteractor<CurrencyRatesAction>(), ICurrencyRatesContract.ICurrencyRatesInteractor {

    private val currencyRates: MutableList<CurrencyRate> = mutableListOf()
    private var selectedCurrencyCode: String = Currency.EUR.name
    private var selectedCurrencyAmount: BigDecimal = BigDecimal.ONE

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadItems() {
        loadItems(selectedCurrencyCode)
    }

    override fun loadItems(currencyCode: String) {
        selectedCurrencyCode = currencyCode
        currencyRatesRepository.getCurrencyRates(currencyCode)
            .toObservable()
            .map { loadedCurrencyRates ->
                currencyRates.clear()
                if (loadedCurrencyRates.isEmpty()) {
                    CurrencyRatesAction.EmptyAction
                } else {
                    loadedCurrencyRates.map { it.rate = it.rate * selectedCurrencyAmount }
                    currencyRates.addAll(loadedCurrencyRates)
                    CurrencyRatesAction.LoadedAction(loadedCurrencyRates)
                }
            }
            .startWith(CurrencyRatesAction.LoadingAction)
            .onErrorReturn {
                errorHandler.handleError(it)
                CurrencyRatesAction.ErrorAction(it)
            }
            .mainThread(rxSchedulers)
            .subscribe {
                actionsSubject.onNext(it)
            }
    }

    override fun onItemClicked(position: Int) {
        val currencyRate = currencyRates[position]
        selectedCurrencyCode = currencyRate.code
        selectedCurrencyAmount = currencyRate.rate
        currencyRates.removeAt(position)
        currencyRates.add(0, currencyRate)
        actionsSubject.onNext(CurrencyRatesAction.LoadedAction(currencyRates))
    }

    override fun amountChanged(amount: BigDecimal) {
        // Empty.
    }
}