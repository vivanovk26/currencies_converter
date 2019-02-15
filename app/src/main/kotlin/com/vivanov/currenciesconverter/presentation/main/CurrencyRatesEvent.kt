package com.vivanov.currenciesconverter.presentation.main

import com.vivanov.currenciesconverter.presentation.core.events.IEvent
import java.math.BigDecimal

sealed class CurrencyRatesEvent : IEvent {

    data class ItemClickedEvent(
        val position: Int
    ) : CurrencyRatesEvent()

    data class AmountChangedEvent(
        val amount: BigDecimal
    ) : CurrencyRatesEvent()
}