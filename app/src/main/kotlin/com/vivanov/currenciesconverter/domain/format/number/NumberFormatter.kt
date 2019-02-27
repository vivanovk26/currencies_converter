package com.vivanov.currenciesconverter.domain.format.number

import com.vivanov.currenciesconverter.R
import com.vivanov.currenciesconverter.data.providers.IResourcesProvider
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

private const val SEPARATOR: String = "."
private const val ZERO_TEXT: String = "0"

class NumberFormatter(
    resourcesProvider: IResourcesProvider,
    locale: Locale
) : INumberFormatter {

    private val numberFormat: NumberFormat = NumberFormat.getInstance(locale)

    init {
        numberFormat.isGroupingUsed = false
        numberFormat.minimumFractionDigits =
            resourcesProvider.getInteger(R.integer.max_fraction_digits)
        numberFormat.maximumFractionDigits =
            resourcesProvider.getInteger(R.integer.max_fraction_digits)
    }

    override fun formatAmount(code: String, amount: BigDecimal): String {
        return numberFormat.format(amount.toDouble())
            .replace(numberFormat.currency.symbol, "")
            .removeUnsupportedSymbols()
    }

    private fun String.removeUnsupportedSymbols(): String {
        val text = this.replace(",", ".")
            .replace(" ", "")
        return if (text.isEmpty() || text == SEPARATOR) {
            ZERO_TEXT
        } else {
            text
        }
    }

    override fun formatEnteredAmountText(amountText: String): String {
        return amountText.removeUnsupportedSymbols()
    }

    override fun formatCurrency(code: String): String {
        return Currency.getInstance(code).symbol
    }
}