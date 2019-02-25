package com.vivanov.currenciesconverter.data.network.services

import com.vivanov.currenciesconverter.data.error.mappers.IErrorMapper
import com.vivanov.currenciesconverter.data.network.api.CurrenciesConverterApi
import com.vivanov.currenciesconverter.data.network.mappers.IApiMapper
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.extensions.IRxSchedulers
import com.vivanov.currenciesconverter.extensions.async
import io.reactivex.Single

class ApiService(
    private val currenciesConverterApi: CurrenciesConverterApi,
    private val apiMapper: IApiMapper,
    private val errorMapper: IErrorMapper,
    private val rxSchedulers: IRxSchedulers
) : IApiService {

    override fun getCurrencyRates(currencyCode: String): Single<List<CurrencyRate>> {
        return currenciesConverterApi.getCurrencyRates(currencyCode)
            .map {
                apiMapper.map(it)
            }
            .onErrorResumeNext {
                Single.error(errorMapper.map(it))
            }
            .async(rxSchedulers)
    }
}