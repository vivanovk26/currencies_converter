package com.vivanov.currenciesconverter.presentation.main

sealed class CurrencyRatesPayload {

    object Amount : CurrencyRatesPayload()

    object Full : CurrencyRatesPayload()
}