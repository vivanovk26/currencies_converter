package com.vivanov.currenciesconverter.presentation.core.adapters

import android.support.v7.util.DiffUtil

abstract class BaseDiffCallback<Item> : DiffUtil.ItemCallback<Item>() {

    abstract fun getId(item: Item): String

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return getId(oldItem) == getId(newItem)
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}