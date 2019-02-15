package com.vivanov.currenciesconverter.domain.format.date

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter(
    locale: Locale
) : IDateFormatter {

    private val `yyyy-MM-dd`: SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", locale)

    override fun parseCurrenciesResponseDate(serverDate: String?): Long {
        return try {
            `yyyy-MM-dd`.parse(serverDate).time
        } catch (e: ParseException) {
            return 0L
        }
    }
}