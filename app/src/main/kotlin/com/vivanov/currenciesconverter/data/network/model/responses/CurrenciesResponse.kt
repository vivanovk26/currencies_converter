package com.vivanov.currenciesconverter.data.network.model.responses

class CurrenciesResponse(
    val base: String? = null,
    val date: String? = null,
    val rates: HashMap<String, Double>? = null
)