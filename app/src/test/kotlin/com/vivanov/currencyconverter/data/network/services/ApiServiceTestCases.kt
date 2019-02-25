package com.vivanov.currencyconverter.data.network.services

import com.vivanov.currenciesconverter.data.error.mappers.IErrorMapper
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
import java.io.IOException
import java.math.BigDecimal

class ApiServiceTestCases : Spek({

    describe("ApiService tests") {

        val validCurrenciesConverterApi = object : CurrenciesConverterApi {

            override fun getCurrencyRates(currencyCode: String): Single<CurrenciesResponse> {
                return Single.just(CurrenciesResponse("", "", hashMapOf("12" to 1.0)))
            }
        }
        val invalidCurrenciesConverterApi = object : CurrenciesConverterApi {

            override fun getCurrencyRates(currencyCode: String): Single<CurrenciesResponse> {
                return Single.error(IllegalStateException())
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

        val mapperException = IOException()
        val errorMapper = object : IErrorMapper {

            override fun map(throwable: Throwable): Throwable {
                return mapperException
            }
        }
        val apiServiceStableApi = ApiService(
            validCurrenciesConverterApi, apiMapper, errorMapper, RxSchedulersTest
        )

        val apiServiceErrorApi = ApiService(
            invalidCurrenciesConverterApi, apiMapper, errorMapper, RxSchedulersTest
        )

        describe("getCurrencyRates validation") {

            it("check request complete") {

                val testObserver = TestObserver<List<CurrencyRate>>()
                apiServiceStableApi.getCurrencyRates("")
                    .subscribe(testObserver)

                testObserver.assertComplete()
            }

            it("check items the same") {

                val testObserver = TestObserver<List<CurrencyRate>>()
                apiServiceStableApi.getCurrencyRates("")
                    .subscribe(testObserver)

                testObserver.assertValue(currencyRatesList)
            }

            it("check error proper handling") {

                val testObserver = TestObserver<List<CurrencyRate>>()
                apiServiceErrorApi.getCurrencyRates("")
                    .subscribe(testObserver)

                testObserver.assertError(mapperException)
            }
        }
    }
})