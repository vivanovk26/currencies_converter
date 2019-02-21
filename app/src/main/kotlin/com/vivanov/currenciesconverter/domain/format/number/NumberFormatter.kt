package com.vivanov.currenciesconverter.domain.format.number

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class NumberFormatter : INumberFormatter {

    private val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun formatAmount(code: String, amount: BigDecimal): String {
        numberFormat.currency = Currency.getInstance(code)
        numberFormat.isGroupingUsed = false
        return numberFormat.format(amount.toDouble()).replace(numberFormat.currency.symbol, "")
    }

    override fun formatCurrency(code: String): String {
        numberFormat.currency = Currency.getInstance(code)
        return numberFormat.currency.symbol
    }
}