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

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadItems() {
        loadItems(selectedCurrencyCode)
    }

    override fun loadItems(currencyCode: String) {
        selectedCurrencyCode = currencyCode
        currencyRatesRepository.getCurrencyRates(currencyCode)
            .toObservable()
            .map {
                currencyRates.clear()
                if (it.isEmpty()) {
                    CurrencyRatesAction.EmptyAction
                } else {
                    currencyRates.addAll(it)
                    CurrencyRatesAction.LoadedAction(it)
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
        selectedCurrencyCode = currencyRates[position].code
        loadItems()
    }

    override fun amountChanged(amount: BigDecimal) {
        // Empty.
    }
}