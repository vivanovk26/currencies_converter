package com.vivanov.currenciesconverter.data.providers

import android.support.annotation.ArrayRes
import android.support.annotation.StringRes

interface IResourcesProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg args: Any): String

    fun getStringList(@ArrayRes resId: Int): List<String>
}
