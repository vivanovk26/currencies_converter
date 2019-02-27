package com.vivanov.currencyconverter.domain.format.number

import com.vivanov.currenciesconverter.data.providers.IResourcesProvider
import com.vivanov.currenciesconverter.domain.format.number.NumberFormatter
import com.vivanov.currenciesconverter.domain.model.Currency
import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal
import java.util.*

class NumberFormatterTestCases : Spek({

    describe("NumberFormatter tests") {

        val resourceProvider = object : IResourcesProvider {

            override fun getString(resId: Int): String {
                return ""
            }

            override fun getString(resId: Int, vararg args: Any): String {
                return ""
            }

            override fun getInteger(resId: Int): Int {
                return 3
            }
        }
        val numberFormatter = NumberFormatter(resourceProvider, Locale.UK)

        describe("formatAmount validation") {

            it("check correct output after formatting") {

                val currencyCode = Currency.USD.name
                val rate = BigDecimal.TEN
                val text = "10.000"
                Assertions.assertEquals(
                    numberFormatter.formatAmount(currencyCode, rate), text
                )
            }
        }

        describe("formatCurrency validation") {

            it("check correct currency for dollar") {

                val currencyCode = Currency.USD.name
                val currency = java.util.Currency.getInstance(currencyCode).symbol
                Assertions.assertEquals(numberFormatter.formatCurrency(currencyCode), currency)
            }
        }
    }
})