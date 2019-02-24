package com.vivanov.currencyconverter.data.network.mappers

import com.vivanov.currenciesconverter.data.error.exceptions.ParseException
import com.vivanov.currenciesconverter.data.network.mappers.ApiMapper
import com.vivanov.currenciesconverter.data.network.model.responses.CurrenciesResponse
import com.vivanov.currenciesconverter.domain.model.Currency
import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ApiMapperTestCases : Spek({

    describe("ApiMapper tests") {

        val apiMapper = ApiMapper()

        describe("map currenciesResponse validation") {

            val validCurrencyCode0 = Currency.EUR.name
            val validCurrencyRate0 = 70.0
            val validCurrencyCode1 = Currency.USD.name
            val validCurrencyRate1 = 60.0
            val validCurrencyCode2 = Currency.RUB.name
            val validCurrencyRate2 = 1.0
            val invalidCurrencyCode = "AA"
            val invalidCurrencyRate = 0.0

            it("check invalid rates in CurrenciesResponse") {
                val invalidCurrenciesResponse = CurrenciesResponse("", "", null)
                Assertions.assertThrows(ParseException::class.java) {
                    apiMapper.map(invalidCurrenciesResponse)
                }
            }

            it("check 2 of currencies after validation ") {

                val currencies2of3 = hashMapOf(
                    validCurrencyCode0 to validCurrencyRate0,
                    validCurrencyCode1 to validCurrencyRate1,
                    invalidCurrencyCode to invalidCurrencyRate
                )
                val validCurrenciesResponse2of3 = CurrenciesResponse(
                    "", "", currencies2of3
                )

                Assertions.assertTrue(apiMapper.map(validCurrenciesResponse2of3).size == 2)
            }

            it("check 3 of currencies after validation") {

                val currencies3of3 = hashMapOf(
                    validCurrencyCode0 to validCurrencyRate0,
                    validCurrencyCode1 to validCurrencyRate1,
                    validCurrencyCode2 to validCurrencyRate2
                )
                val validCurrenciesResponse3of3 = CurrenciesResponse(
                    "", "", currencies3of3
                )

                Assertions.assertTrue(apiMapper.map(validCurrenciesResponse3of3).size == 3)
            }
        }
    }
})