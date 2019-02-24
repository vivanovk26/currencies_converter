package com.vivanov.currencyconverter.data.network.services

import com.vivanov.currenciesconverter.data.network.api.CurrenciesConverterApi
import com.vivanov.currenciesconverter.data.network.mappers.IApiMapper
import com.vivanov.currenciesconverter.data.network.model.responses.CurrenciesResponse
import com.vivanov.currenciesconverter.data.network.services.ApiService
import com.vivanov.currenciesconverter.domain.model.Currency
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.extensions.RxSchedulersTest
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

class ApiServiceTestCases : Spek({

    describe("ApiService tests") {

        val currenciesConverterApi = object : CurrenciesConverterApi {

            override fun getCurrencyRates(currencyCode: String): Single<CurrenciesResponse> {
                return Single.just(CurrenciesResponse("", "", hashMapOf("12" to 1.0)))
            }
        }
        val currencyRatesList = listOf(
            CurrencyRate(Currency.RUB, BigDecimal.ONE),
            CurrencyRate(Currency.USD, BigDecimal.TEN)
        )
        val apiMapper = object : IApiMapper {

            override fun map(currenciesResponse: CurrenciesResponse): List<CurrencyRate> {
                return currencyRatesList
            }
        }
        val apiService = ApiService(currenciesConverterApi, apiMapper, RxSchedulersTest)

        describe("getCurrencyRates validation") {

            it("check request complete") {

                val testObserver = TestObserver<List<CurrencyRate>>()
                apiService.getCurrencyRates("")
                    .subscribe(testObserver)

                testObserver.assertComplete()
            }

            it("check items the same") {

                val testObserver = TestObserver<List<CurrencyRate>>()
                apiService.getCurrencyRates("")
                    .subscribe(testObserver)

                testObserver.assertValue(currencyRatesList)
            }
        }
    }
})