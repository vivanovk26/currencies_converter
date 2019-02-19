package com.vivanov.currenciesconverter.presentation.main

import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.presentation.core.adapters.BaseDiffCallback

class CurrencyRatesDiffCallback(
    private val oldList: List<CurrencyRate>,
    private val newList: List<CurrencyRate>
) : BaseDiffCallback<CurrencyRate>(oldList, newList) {

    override fun areItemsTheSame(positionOld: Int, positionNew: Int): Boolean {
        return oldList[positionOld].code == newList[positionNew].code
    }
}