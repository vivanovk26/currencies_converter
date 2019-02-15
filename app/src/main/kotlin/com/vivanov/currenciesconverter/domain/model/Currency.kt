package com.vivanov.currenciesconverter.domain.model

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.example.currenciesconverter.R

enum class Currency(
    @StringRes
    val description: Int,
    @DrawableRes
    val icon: Int
) {

    AUD(R.string.currency_aud, R.drawable.abc_ic_ab_back_material),
    BGN(R.string.currency_bgn, R.drawable.abc_ic_ab_back_material),
    BRL(R.string.currency_brl, R.drawable.abc_ic_ab_back_material),
    CAD(R.string.currency_cad, R.drawable.abc_ic_ab_back_material),
    CHF(R.string.currency_chf, R.drawable.abc_ic_ab_back_material),
    CNY(R.string.currency_cny, R.drawable.abc_ic_ab_back_material),
    CZK(R.string.currency_czk, R.drawable.abc_ic_ab_back_material),
    DKK(R.string.currency_dkk, R.drawable.abc_ic_ab_back_material),
    EUR(R.string.currency_eur, R.drawable.abc_ic_ab_back_material),
    GBP(R.string.currency_gbp, R.drawable.abc_ic_ab_back_material),
    HKD(R.string.currency_hkd, R.drawable.abc_ic_ab_back_material),
    HRK(R.string.currency_hrk, R.drawable.abc_ic_ab_back_material),
    HUF(R.string.currency_huf, R.drawable.abc_ic_ab_back_material),
    IDR(R.string.currency_idr, R.drawable.abc_ic_ab_back_material),
    ILS(R.string.currency_ils, R.drawable.abc_ic_ab_back_material),
    INR(R.string.currency_inr, R.drawable.abc_ic_ab_back_material),
    ISK(R.string.currency_isk, R.drawable.abc_ic_ab_back_material),
    JPY(R.string.currency_jpy, R.drawable.abc_ic_ab_back_material),
    KRW(R.string.currency_krw, R.drawable.abc_ic_ab_back_material),
    MXN(R.string.currency_mxn, R.drawable.abc_ic_ab_back_material),
    MYR(R.string.currency_myr, R.drawable.abc_ic_ab_back_material),
    NOK(R.string.currency_nok, R.drawable.abc_ic_ab_back_material),
    NZD(R.string.currency_nzd, R.drawable.abc_ic_ab_back_material),
    PHP(R.string.currency_php, R.drawable.abc_ic_ab_back_material),
    PIN(R.string.currency_pln, R.drawable.abc_ic_ab_back_material),
    RON(R.string.currency_ron, R.drawable.abc_ic_ab_back_material),
    RUB(R.string.currency_rub, R.drawable.abc_ic_ab_back_material),
    SEK(R.string.currency_sek, R.drawable.abc_ic_ab_back_material),
    SGD(R.string.currency_sgd, R.drawable.abc_ic_ab_back_material),
    THB(R.string.currency_thb, R.drawable.abc_ic_ab_back_material),
    TRY(R.string.currency_try, R.drawable.abc_ic_ab_back_material),
    USD(R.string.currency_usd, R.drawable.abc_ic_ab_back_material),
    ZAR(R.string.currency_zar, R.drawable.abc_ic_ab_back_material),
}