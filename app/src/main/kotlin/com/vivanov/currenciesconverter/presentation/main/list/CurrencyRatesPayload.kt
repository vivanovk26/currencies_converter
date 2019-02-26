package com.vivanov.currenciesconverter.presentation.main.list

sealed class CurrencyRatesPayload {

    object Amount : CurrencyRatesPayload()

    object Full : CurrencyRatesPayload()
}