package com.vivanov.currenciesconverter.presentation.main

import android.view.View
import android.view.inputmethod.EditorInfo
import com.example.currenciesconverter.R
import com.jakewharton.rxbinding2.view.clicks
import com.vivanov.currenciesconverter.data.network.services.IImageLoaderService
import com.vivanov.currenciesconverter.domain.contracts.ICurrencyRatesContract
import com.vivanov.currenciesconverter.extensions.hideKeyboard
import com.vivanov.currenciesconverter.presentation.core.adapters.BaseAdapter
import com.vivanov.currenciesconverter.presentation.core.adapters.BaseDiffCallback
import com.vivanov.currenciesconverter.presentation.core.adapters.BaseViewHolder
import kotlinx.android.synthetic.main.item_currency_rate.view.*

class CurrencyRatesAdapter(
    private val imageLoaderService: IImageLoaderService
) : BaseAdapter<CurrencyRateVM, CurrencyRatesAdapter.ViewHolder>() {

    override val layoutId: Int = R.layout.item_currency_rate

    lateinit var currencyRatesView: ICurrencyRatesContract.ICurrencyRatesView

    override fun createViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }

    override fun createDiffCallback(
        oldList: List<CurrencyRateVM>,
        newList: List<CurrencyRateVM>
    ): BaseDiffCallback<CurrencyRateVM> {
        return CurrencyRatesDiffCallback(oldList, newList)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<CurrencyRateVM>(itemView) {

        override fun bind(item: CurrencyRateVM) {
            imageLoaderService.loadFlagIcon(itemView.sdrv, item.iconCode)
            itemView.tv_code.text = item.code
            itemView.tv_description.text = item.description
            itemView.tv_currency_symbol.text = item.currencySymbol
            if (!itemView.et_amount.hasFocus()) {
                itemView.et_amount.setText(item.amount)
            }
            itemView.et_amount.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    currencyRatesView.onItemFocused(adapterPosition, itemView.et_amount)
                }
            }
            itemView.et_amount.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    itemView.et_amount.hideKeyboard()
                    return@setOnEditorActionListener true
                } else {
                    return@setOnEditorActionListener false
                }
            }

            itemView.clicks()
                .subscribe {
                    currencyRatesView.onItemClicked(adapterPosition)
                    itemView.et_amount.requestFocus()
                }
        }

        override fun bindPayloads(item: CurrencyRateVM, payload: Any) {
            when (payload) {
                is CurrencyRatesPayload.Amount -> {
                    itemView.et_amount.setText(item.amount)
                }
                else -> {
                    bind(item)
                }
            }
        }
    }
}