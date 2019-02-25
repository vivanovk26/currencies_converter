package com.vivanov.currenciesconverter.domain.interactors.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.vivanov.currenciesconverter.data.repositories.ICurrencyRatesRepository
import com.vivanov.currenciesconverter.domain.contracts.ICurrencyRatesContract
import com.vivanov.currenciesconverter.domain.interactors.core.BaseInteractor
import com.vivanov.currenciesconverter.domain.model.Currency
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.presentation.main.CurrencyRatesAction
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal

private const val SELECTED_ITEM_POSITION: Int = 0
private const val UPDATE_PERIOD: Long = 1L

class CurrencyRatesInteractor(
    private val currencyRatesRepository: ICurrencyRatesRepository
) : BaseInteractor<CurrencyRatesAction>(), ICurrencyRatesContract.ICurrencyRatesInteractor {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var currentCurrencyRate: CurrencyRate = CurrencyRate(
        Currency.EUR, BigDecimal.ONE
    )
    private val currencyRates: MutableList<CurrencyRate> = mutableListOf(currentCurrencyRate)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startUpdates() {
        loadItems()
    }

    override fun loadItems() {
        compositeDisposable.add(
            //Observable.interval(UPDATE_PERIOD, TimeUnit.SECONDS)
            //    .observeOn(Schedulers.io())
            //    .concatMap {
                    currencyRatesRepository.getCurrencyRates(currentCurrencyRate.code)
                        .observeOn(Schedulers.io())
                        .toObservable()
                        .map { newCurrencyRates ->
                            if (newCurrencyRates.isEmpty()) {
                                CurrencyRatesAction.EmptyAction
                            } else {
                                mergeList(newCurrencyRates)
                                CurrencyRatesAction.LoadedListAction(currencyRates)
                            }
                        }
                        //    }
                .toFlowable(BackpressureStrategy.LATEST)
                .startWith(CurrencyRatesAction.LoadingAction)
                .onErrorReturn {
                    CurrencyRatesAction.ErrorAction(it)
                }
                        .subscribeOn(Schedulers.io())
                .subscribe {
                    actionsSubject.onNext(it)
                }
        )
    }

    private fun mergeList(newCurrencyRates: List<CurrencyRate>) {
        newCurrencyRates.map {
            it.amount = it.rate.multiply(currentCurrencyRate.amount)
        }
        newCurrencyRates.forEach { newCurrencyRate ->
            val mergedCurrencyRate = currencyRates.find {
                it.code == newCurrencyRate.code
            }
            if (mergedCurrencyRate == null) {
                currencyRates.add(newCurrencyRate)
            } else {
                mergedCurrencyRate.rate = newCurrencyRate.rate
                mergedCurrencyRate.amount = newCurrencyRate.amount
            }
        }
    }

    override fun onItemClicked(position: Int) {
        if (currencyRates[position].code != currentCurrencyRate.code) {
            reloadCurrencyRates(position)
            replaceCurrencyRates(position)
        } else if (position != SELECTED_ITEM_POSITION) {
            replaceCurrencyRates(position)
        }
    }

    private fun reloadCurrencyRates(position: Int) {
        compositeDisposable.clear()
        currentCurrencyRate = currencyRates[position]
        loadItems()
    }

    private fun replaceCurrencyRates(position: Int) {
        currencyRates.removeAt(position)
        currencyRates.add(SELECTED_ITEM_POSITION, currentCurrencyRate)
        actionsSubject.onNext(CurrencyRatesAction.UpdateListAction(currencyRates))
    }

    override fun amountChanged(position: Int, amount: BigDecimal) {
        currencyRates[position].amount = amount
        if (currencyRates[position].code == currentCurrencyRate.code) {
            updateCurrencyRateAmounts(position, amount)
        } else {
            reloadCurrencyRates(position)
        }
    }

    private fun updateCurrencyRateAmounts(position: Int, amount: BigDecimal) {
        currencyRates.filterIndexed { index, _ ->
            index != position
        }.map {
            it.amount = it.rate.multiply(amount)
        }
        actionsSubject.onNext(CurrencyRatesAction.UpdateListAction(currencyRates))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopUpdates() {
        compositeDisposable.clear()
    }
}