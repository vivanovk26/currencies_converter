package com.vivanov.currenciesconverter.data.providers

import android.content.Context
import android.support.annotation.StringRes

class ResourcesProvider(
    private val context: Context
) : IResourcesProvider {

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(@StringRes resId: Int, vararg args: Any): String {
        return context.getString(resId, *args)
    }

    override fun getInteger(resId: Int): Int {
        return context.resources.getInteger(resId)
    }
}