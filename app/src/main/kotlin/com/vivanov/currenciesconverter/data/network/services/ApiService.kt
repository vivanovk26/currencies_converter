package com.vivanov.currenciesconverter.data.network.services

import com.vivanov.currenciesconverter.data.error.mappers.IErrorMapper
import com.vivanov.currenciesconverter.data.network.api.CurrenciesConverterApi
import com.vivanov.currenciesconverter.data.network.mappers.IApiMapper
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import io.reactivex.Single

class ApiService(
    private val currenciesConverterApi: CurrenciesConverterApi,
    private val apiMapper: IApiMapper,
    private val errorMapper: IErrorMapper
) : IApiService {

    override fun getCurrencyRates(currencyCode: String): Single<List<CurrencyRate>> {
        return currenciesConverterApi.getCurrencyRates(currencyCode)
            .map {
                apiMapper.map(it)
            }
            .onErrorResumeNext {
                Single.error(errorMapper.map(it))
            }
    }
}