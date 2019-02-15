package com.vivanov.currenciesconverter.domain.format.date

interface IDateFormatter {

    fun parseCurrenciesResponseDate(serverDate: String?): Long
}