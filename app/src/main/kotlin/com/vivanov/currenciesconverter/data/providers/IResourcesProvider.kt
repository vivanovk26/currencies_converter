package com.vivanov.currenciesconverter.data.providers

import android.support.annotation.IntegerRes
import android.support.annotation.StringRes

interface IResourcesProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg args: Any): String

    fun getInteger(@IntegerRes resId: Int): Int
}
