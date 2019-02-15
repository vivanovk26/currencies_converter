package com.vivanov.currenciesconverter.presentation.main

import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.presentation.core.adapters.BaseDiffCallback

class CurrencyRatesDiffCallback : BaseDiffCallback<CurrencyRate>() {

    override fun getId(item: CurrencyRate): String {
        return item.code
    }
}