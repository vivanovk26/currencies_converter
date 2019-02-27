package com.vivanov.currenciesconverter.data.network.services

import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import io.reactivex.Single

interface IApiService {

    fun getCurrencyRates(currencyCode: String): Single<List<CurrencyRate>>
}