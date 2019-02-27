package com.vivanov.currenciesconverter.domain.format.number

import java.math.BigDecimal

interface INumberFormatter {

    fun formatAmount(code: String, amount: BigDecimal): String

    fun formatEnteredAmountText(amountText: String): String

    fun formatCurrency(code: String): String
}