package com.vivanov.currenciesconverter.presentation.main

data class CurrencyRateVM(
    val code: String,
    val description: String,
    val iconCode: String,
    val amount: String,
    val currencySymbol: String
    //val rateIsLoading: Boolean
)