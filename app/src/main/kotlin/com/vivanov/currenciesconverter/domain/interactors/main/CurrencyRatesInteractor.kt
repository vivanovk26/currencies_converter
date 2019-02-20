package com.vivanov.currenciesconverter.domain.interactors.main

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

private const val SELECTED_ITEM_POSITION: Int = 0

class CurrencyRatesInteractor(
    private val currencyRatesRepository: ICurrencyRatesRepository,
    private val errorHandler: IErrorHandler,
    private val rxSchedulers: IRxSchedulers
) : BaseInteractor<CurrencyRatesAction>(), ICurrencyRatesContract.ICurrencyRatesInteractor {

    private var currentCurrencyRate: CurrencyRate = CurrencyRate(
        Currency.EUR, BigDecimal(1.0)
    )
    private val currencyRates: MutableList<CurrencyRate> = mutableListOf(currentCurrencyRate)

    init {
        loadItems(currentCurrencyRate.code)
    }

    override fun loadItems(currencyCode: String) {
        currencyRatesRepository.getCurrencyRates(currencyCode)
            .toObservable()
            .map { loadedCurrencyRates ->
                if (loadedCurrencyRates.isEmpty()) {
                    CurrencyRatesAction.EmptyAction
                } else {
                    loadedCurrencyRates.map {
                        it.amount = it.rate.multiply(currentCurrencyRate.amount)
                    }
                    currencyRates.addAll(loadedCurrencyRates)
                    val resultList = mutableListOf<CurrencyRate>().apply {
                        addAll(currencyRates)
                    }
                    CurrencyRatesAction.UpdateListAction(resultList)
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
        val selectedCurrencyRate = currencyRates[position]
        currencyRates.removeAt(position)
        currencyRates.add(SELECTED_ITEM_POSITION, selectedCurrencyRate)
        val resultList = mutableListOf<CurrencyRate>().apply {
            addAll(currencyRates)
        }
        actionsSubject.onNext(CurrencyRatesAction.UpdateListAction(resultList))
    }

    override fun amountChanged(position: Int, amount: BigDecimal) {
        currentCurrencyRate = currencyRates[position]
        currencyRates.map {
            //it.amount = it.rate.multiply(currentCurrencyRate.amount)
            it.amount = amount
        }
        val resultList = mutableListOf<CurrencyRate>().apply {
            addAll(currencyRates)
        }
        actionsSubject.onNext(CurrencyRatesAction.UpdateListAction(resultList))
    }
}