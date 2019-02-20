package com.vivanov.currenciesconverter.presentation.main

sealed class CurrencyRatesPayload {

    object Rate : CurrencyRatesPayload()

    object Empty : CurrencyRatesPayload()
}