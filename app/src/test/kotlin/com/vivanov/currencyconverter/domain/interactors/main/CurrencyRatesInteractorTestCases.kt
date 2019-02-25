package com.vivanov.currencyconverter.domain.interactors.main

import com.vivanov.currenciesconverter.data.repositories.ICurrencyRatesRepository
import com.vivanov.currenciesconverter.domain.interactors.main.CurrencyRatesInteractor
import com.vivanov.currenciesconverter.domain.model.Currency
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.presentation.main.CurrencyRatesAction
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

class CurrencyRatesInteractorTestCases : Spek({

    describe("CurrencyRateInteractor tests") {

        describe("correct values from repository") {

            val currencyRates = listOf(
                CurrencyRate(Currency.USD, BigDecimal.ONE),
                CurrencyRate(Currency.RUB, BigDecimal.TEN)
            )

            val currencyRatesRepository = object : ICurrencyRatesRepository {

                override fun getCurrencyRates(currencyCode: String): Single<List<CurrencyRate>> {
                    return Single.just(currencyRates)
                }
            }

            val currencyRatesInteractor = CurrencyRatesInteractor(currencyRatesRepository)

            it("check first action is LoadingAction") {

                val testObserver = TestObserver<CurrencyRatesAction>()
                currencyRatesInteractor.actionsSubject
                    .subscribe(testObserver)
                currencyRatesInteractor.loadItems()

                testObserver.assertValueAt(0, CurrencyRatesAction.LoadingAction)
            }

            /*it("check second action is LoadedAction") {

                val testObserver = TestObserver<CurrencyRatesAction>()
                currencyRatesInteractor.actionsSubject
                    .subscribe(testObserver)
                currencyRatesInteractor.loadItems()

                testObserver.assertValueAt(1, CurrencyRatesAction.LoadedListAction(currencyRates))
            }*/
        }
    }
})