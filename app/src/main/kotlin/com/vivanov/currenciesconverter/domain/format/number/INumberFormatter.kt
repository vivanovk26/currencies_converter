package com.vivanov.currenciesconverter.domain.format.number

import java.math.BigDecimal

interface INumberFormatter {

    fun formatAmount(code: String, amount: BigDecimal): String

    fun formatCurrency(code: String): String
}