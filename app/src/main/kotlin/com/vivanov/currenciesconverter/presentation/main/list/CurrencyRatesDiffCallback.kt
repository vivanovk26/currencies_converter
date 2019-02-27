package com.vivanov.currenciesconverter.presentation.main.list

import com.vivanov.currenciesconverter.presentation.core.adapters.BaseDiffCallback
import com.vivanov.currenciesconverter.presentation.main.CurrencyRateVM

class CurrencyRatesDiffCallback(
    private val oldList: List<CurrencyRateVM>,
    private val newList: List<CurrencyRateVM>
) : BaseDiffCallback<CurrencyRateVM>(oldList, newList) {

    override fun areItemsTheSame(positionOld: Int, positionNew: Int): Boolean {
        return oldList[positionOld].code == newList[positionNew].code
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return if (oldList[oldItemPosition].amount == newList[newItemPosition].amount) {
            CurrencyRatesPayload.Full
        } else {
            CurrencyRatesPayload.Amount
        }
    }
}