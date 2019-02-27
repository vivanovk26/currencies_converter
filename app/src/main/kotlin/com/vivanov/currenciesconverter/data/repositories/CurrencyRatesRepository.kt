package com.vivanov.currenciesconverter.data.repositories

import com.vivanov.currenciesconverter.data.network.services.IApiService
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import io.reactivex.Single

/**
 * For this app it's redundant maybe. But this is a place where we handle incoming data, save
 * some models in database and etc.
 */
class CurrencyRatesRepository(
    private val apiService: IApiService
) : ICurrencyRatesRepository {

    override fun getCurrencyRates(currencyCode: String): Single<List<CurrencyRate>> {
        return apiService.getCurrencyRates(currencyCode)
    }
}
