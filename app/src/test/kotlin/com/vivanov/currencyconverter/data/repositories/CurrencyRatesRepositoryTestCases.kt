package com.vivanov.currencyconverter.data.repositories

import com.vivanov.currenciesconverter.data.network.services.IApiService
import com.vivanov.currenciesconverter.data.repositories.CurrencyRatesRepository
import com.vivanov.currenciesconverter.domain.model.Currency
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

class CurrencyRatesRepositoryTestCases : Spek({

    describe("CurrencyRatesRepository tests") {

        val currencyRate1 = CurrencyRate(Currency.USD, BigDecimal.ONE)
        val currencyRate2 = CurrencyRate(Currency.RUB, BigDecimal.TEN)
        val currenciesList = listOf(
            currencyRate1,
            currencyRate2
        )

        val apiService = object : IApiService {

            override fun getCurrencyRates(currencyCode: String): Single<List<CurrencyRate>> {
                return Single.just(currenciesList)
            }
        }

        val currencyRatesRepository = CurrencyRatesRepository(apiService)

        describe("getCurrencyRates validation") {

            it("check getCurrencyRates completes") {

                val testObserver = TestObserver<List<CurrencyRate>>()
                currencyRatesRepository.getCurrencyRates("")
                    .subscribe(testObserver)

                testObserver.assertComplete()
            }

            it("check items the same as apiService provides") {

                val testObserver = TestObserver<List<CurrencyRate>>()
                apiService.getCurrencyRates("")
                    .subscribe(testObserver)

                testObserver.assertValue(currenciesList)
            }
        }
    }
})