package com.vivanov.currenciesconverter.presentation.core.adapters

import android.support.v7.util.DiffUtil

abstract class BaseDiffCallback<Item>(
    private val oldList: List<Item>,
    private val newList: List<Item>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(positionOld: Int, positionNew: Int): Boolean {
        return oldList[positionOld] == newList[positionNew]
    }
}