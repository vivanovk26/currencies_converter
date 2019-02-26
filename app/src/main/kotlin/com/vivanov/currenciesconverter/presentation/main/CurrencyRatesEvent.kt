package com.vivanov.currenciesconverter.presentation.main

import com.vivanov.currenciesconverter.presentation.core.events.IEvent

sealed class CurrencyRatesEvent : IEvent {

    object RefreshEvent : CurrencyRatesEvent()

    data class FocusChangedEvent(
        val position: Int
    ) : CurrencyRatesEvent()

    data class AmountChangedEvent(
        val position: Int,
        val amount: String
    ) : CurrencyRatesEvent()

    data class ItemClickedEvent(
        val position: Int
    ) : CurrencyRatesEvent()
}