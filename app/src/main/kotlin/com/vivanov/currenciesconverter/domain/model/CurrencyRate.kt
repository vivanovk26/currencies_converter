package com.vivanov.currenciesconverter.domain.model

import android.support.annotation.StringRes
import java.math.BigDecimal

data class CurrencyRate(
    val code: String,
    @StringRes
    val description: Int,
    val iconCode: String,
    var rate: BigDecimal,
    var amount: BigDecimal = BigDecimal.ONE
) {

    constructor(
        currency: Currency,
        rate: BigDecimal
    ) : this(currency.name, currency.description, currency.iconCode, rate)
}