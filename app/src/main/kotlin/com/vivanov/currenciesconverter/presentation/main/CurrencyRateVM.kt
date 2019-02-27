package com.vivanov.currenciesconverter.presentation.main

/**
 * Purpose of this class to make View as simple as possible. View should only set some values
 * without handling them via formatters, cases, etc..
 */
data class CurrencyRateVM(
    val code: String,
    val description: String,
    val iconCode: String,
    val amount: String,
    val currencySymbol: String
)