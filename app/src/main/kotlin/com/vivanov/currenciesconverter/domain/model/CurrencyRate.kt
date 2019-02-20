package com.vivanov.currenciesconverter.domain.model

import android.support.annotation.StringRes
import java.io.Serializable
import java.math.BigDecimal

data class CurrencyRate(
    val code: String,
    @StringRes
    val description: Int,
    val icon: String,
    val rate: BigDecimal,
    var amount: BigDecimal = BigDecimal.ONE
) : Serializable {

    constructor(
        currency: Currency,
        rate: BigDecimal
    ) : this(currency.name, currency.description, currency.icon, rate)
}