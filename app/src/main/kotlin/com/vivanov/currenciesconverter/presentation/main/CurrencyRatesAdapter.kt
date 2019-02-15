package com.vivanov.currenciesconverter.presentation.main

import android.view.View
import com.example.currenciesconverter.R
import com.jakewharton.rxbinding2.view.clicks
import com.vivanov.currenciesconverter.domain.contracts.ICurrencyRatesContract
import com.vivanov.currenciesconverter.domain.model.CurrencyRate
import com.vivanov.currenciesconverter.presentation.core.adapters.BaseAdapter
import com.vivanov.currenciesconverter.presentation.core.adapters.BaseViewHolder
import kotlinx.android.synthetic.main.item_currency_rate.view.*

class CurrencyRatesAdapter : BaseAdapter<CurrencyRate, CurrencyRatesAdapter.ViewHolder>(
    CurrencyRatesDiffCallback()
) {

    override val layoutId: Int = R.layout.item_currency_rate

    lateinit var currencyRatesView: ICurrencyRatesContract.ICurrencyRatesView

    override fun createViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<CurrencyRate>(itemView) {

        override fun bind(item: CurrencyRate) {
            itemView.sdrv.setImageResource(item.icon)
            itemView.tv_code.text = item.code
            itemView.tv_description.text = itemView.context.getString(item.description)
            itemView.clicks()
                .subscribe {
                    currencyRatesView.onItemClicked(adapterPosition)
                }
        }
    }
}