package com.vivanov.currenciesconverter.data.network.mappers

import com.vivanov.currenciesconverter.data.network.model.responses.CurrenciesResponse
import com.vivanov.currenciesconverter.domain.model.CurrencyRate

interface IApiMapper {

    fun map(currenciesResponse: CurrenciesResponse): List<CurrencyRate>
}