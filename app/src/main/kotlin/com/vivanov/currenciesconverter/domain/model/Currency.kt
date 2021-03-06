package com.vivanov.currenciesconverter.domain.model

import android.support.annotation.StringRes
import com.vivanov.currenciesconverter.R

enum class Currency(
    @StringRes
    val description: Int,
    val iconCode: String
) {

    AUD(
        R.string.currency_aud,
        "AU"
    ),
    BGN(
        R.string.currency_bgn,
        "BG"
    ),
    BRL(
        R.string.currency_brl,
        "BR"
    ),
    CAD(
        R.string.currency_cad,
        "CA"
    ),
    CHF(
        R.string.currency_chf,
        "CH"
    ),
    CNY(
        R.string.currency_cny,
        "CN"
    ),
    CZK(
        R.string.currency_czk,
        "CZ"
    ),
    DKK(
        R.string.currency_dkk,
        "DK"
    ),
    EUR(
        R.string.currency_eur,
        "EU"
    ),
    GBP(
        R.string.currency_gbp,
        "GB"
    ),
    HKD(
        R.string.currency_hkd,
        "HK"
    ),
    HRK(
        R.string.currency_hrk,
        "HR"
    ),
    HUF(
        R.string.currency_huf,
        "HU"
    ),
    IDR(
        R.string.currency_idr,
        "ID"
    ),
    ILS(
        R.string.currency_ils,
        "IL"
    ),
    INR(
        R.string.currency_inr,
        "IN"
    ),
    ISK(
        R.string.currency_isk,
        "IS"
    ),
    JPY(
        R.string.currency_jpy,
        "JP"
    ),
    KRW(
        R.string.currency_krw,
        "KR"
    ),
    MXN(
        R.string.currency_mxn,
        "MX"
    ),
    MYR(
        R.string.currency_myr,
        "MY"
    ),
    NOK(
        R.string.currency_nok,
        "NO"
    ),
    NZD(
        R.string.currency_nzd,
        "NZ"
    ),
    PHP(
        R.string.currency_php,
        "PH"
    ),
    PIN(
        R.string.currency_pln,
        "PI"
    ),
    RON(
        R.string.currency_ron,
        "RO"
    ),
    RUB(
        R.string.currency_rub,
        "RU"
    ),
    SEK(
        R.string.currency_sek,
        "SE"
    ),
    SGD(
        R.string.currency_sgd,
        "SG"
    ),
    THB(
        R.string.currency_thb,
        "TG"
    ),
    TRY(
        R.string.currency_try,
        "TR"
    ),
    USD(
        R.string.currency_usd,
        "US"
    ),
    ZAR(
        R.string.currency_zar,
        "ZA"
    ),
}