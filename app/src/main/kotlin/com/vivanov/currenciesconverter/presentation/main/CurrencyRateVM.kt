package com.vivanov.currenciesconverter.presentation.main

data class CurrencyRateVM(
    val code: String,
    val description: String,
    val icon: String,
    val amount: String,
    val currencySymbol: String
)