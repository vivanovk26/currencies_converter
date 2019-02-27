package com.vivanov.currencyconverter.domain.interactors.main

import com.vivanov.currenciesconverter.data.repositories.ICurrencyRatesRepository
import com.vivanov.currenciesconverter.domain.interactors.main.CurrencyRatesInteractor
import com.vivanov.currenciesconverter.domain.model.Currency
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

// Unfortunately I don't have a large experience with unit testing like that so here I've got a
// problem. I will provide correct test cases later.
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
        }
    }
})