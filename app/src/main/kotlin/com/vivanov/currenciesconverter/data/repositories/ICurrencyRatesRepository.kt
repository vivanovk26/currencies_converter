package com.vivanov.currenciesconverter.data.repositories

import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import io.reactivex.Single

interface ICurrencyRatesRepository {

    fun getCurrencyRates(currencyCode: String): Single<List<CurrencyRate>>
}