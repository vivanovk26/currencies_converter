package com.vivanov.currenciesconverter.data.repositories

import com.vivanov.currenciesconverter.data.network.services.IApiService
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import io.reactivex.Single

class CurrencyRatesRepository(
    private val apiService: IApiService
) : ICurrencyRatesRepository {

    override fun getCurrencyRates(currencyCode: String): Single<List<CurrencyRate>> {
        return apiService.getCurrencyRates(currencyCode)
    }
}
