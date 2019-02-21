package com.vivanov.currenciesconverter.presentation.main

import java.math.BigDecimal

data class CurrencyRateVM(
    val code: String,
    val description: String,
    val icon: String,
    val amount: BigDecimal
)