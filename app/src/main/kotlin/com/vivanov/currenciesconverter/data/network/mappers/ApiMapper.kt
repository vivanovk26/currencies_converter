package com.vivanov.currenciesconverter.data.network.mappers

import com.vivanov.currenciesconverter.data.error.exceptions.ParseException
import com.vivanov.currenciesconverter.data.network.model.responses.CurrenciesResponse
import com.vivanov.currenciesconverter.domain.model.Currency
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import java.math.BigDecimal

class ApiMapper : IApiMapper {

    override fun map(currenciesResponse: CurrenciesResponse): List<CurrencyRate> {
        if (currenciesResponse.rates == null) {
            throw ParseException()
        } else {
            return currenciesResponse.rates.entries
                .filter {
                    valid(it.key)
                }
                .map {
                    val currency = Currency.valueOf(it.key)
                    val rate = BigDecimal(it.value)
                    CurrencyRate(currency, rate)
                }
        }
    }

    /**
     * Validation for incorrect server model.
     * We ignore all unexpected currencies.
     */
    private fun valid(currency: String): Boolean {
        return currency in Currency.values().map { it.name }
    }
}