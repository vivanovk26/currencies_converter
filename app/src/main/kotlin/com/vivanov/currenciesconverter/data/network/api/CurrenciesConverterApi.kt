package com.vivanov.currenciesconverter.data.network.api

import com.vivanov.currenciesconverter.data.network.model.responses.CurrenciesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesConverterApi {

    @GET("latest")
    fun getCurrencyRates(
        @Query("base") currencyCode: String
    ): Single<CurrenciesResponse>
}
