package com.vivanov.currenciesconverter.presentation.core.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter<Item, ViewHolder : BaseViewHolder<Item>>(
    diffCallback: BaseDiffCallback<Item>
) : ListAdapter<Item, ViewHolder>(diffCallback) {

    protected abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return createViewHolder(
            LayoutInflater.from(parent.context).inflate(
                layoutId,
                parent,
                false
            )
        )
    }

    protected abstract fun createViewHolder(itemView: View): ViewHolder

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}