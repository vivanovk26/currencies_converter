package com.vivanov.currenciesconverter.presentation.core.adapters

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<Item>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: Item)

    abstract fun bindPayloads(item: Item, payload: Any)
}