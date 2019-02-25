package com.vivanov.currencyconverter.domain.format.number

import com.vivanov.currenciesconverter.domain.format.number.NumberFormatter
import com.vivanov.currenciesconverter.domain.model.Currency
import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

class NumberFormatterTestCases : Spek({

    describe("NumberFormatter tests") {

        val numberFormatter = NumberFormatter()

        describe("formatAmount validation") {

            it("check correct output after formatting") {

                Assertions.assertEquals(
                    numberFormatter.formatAmount(Currency.USD.name, BigDecimal.TEN), "10.00"
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