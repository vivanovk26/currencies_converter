package com.vivanov.currenciesconverter.presentation.core.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter<Item, ViewHolder : BaseViewHolder<Item>> :
    RecyclerView.Adapter<ViewHolder>() {

    protected abstract val layoutId: Int
    private var items: List<Item> = listOf()
    private var onBind: Boolean = false

    override fun getItemCount(): Int = items.size

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
        onBind = true
        holder.bind(items[position])
        onBind = false
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
        onBind = true
        if (payloads.isEmpty()) {
            holder.bind(items[position])
        } else {
            for (payload in payloads) {
                holder.bindPayloads(items[position], payload)
            }
        }
        onBind = false
    }

    fun setList(newList: List<Item>) {
        val diffResult = DiffUtil.calculateDiff(createDiffCallback(items, newList), true)
        items = newList
        if (!onBind) {
            diffResult.dispatchUpdatesTo(this)
        }
    }

    protected abstract fun createDiffCallback(
        oldList: List<Item>,
        newList: List<Item>
    ): BaseDiffCallback<Item>
}