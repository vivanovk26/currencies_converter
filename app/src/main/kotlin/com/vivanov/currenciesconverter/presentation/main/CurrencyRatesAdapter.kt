package com.vivanov.currenciesconverter.presentation.main

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.jakewharton.rxbinding2.view.clicks
import com.vivanov.currenciesconverter.R
import com.vivanov.currenciesconverter.data.network.services.IImageLoaderService
import com.vivanov.currenciesconverter.domain.contracts.ICurrencyRatesContract
import com.vivanov.currenciesconverter.domain.interactors.main.SELECTED_ITEM_POSITION
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

    private var selectedPosition: Int? = null

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

        private val textWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Empty.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Empty.
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val text = it.toString()
                    val result = if (text.isEmpty()) {
                        "0"
                    } else {
                        text
                    }
                    if (selectedPosition == adapterPosition) {
                        currencyRatesView.onAmountChanged(adapterPosition, result)
                    }
                }
            }
        }
        private var selectedSelectionPosition: Int = 0

        override fun bind(item: CurrencyRateVM) {
            imageLoaderService.loadFlagIcon(itemView.sdrv, item.iconCode)
            itemView.tv_code.text = item.code
            itemView.tv_description.text = item.description
            itemView.tv_currency_symbol.text = item.currencySymbol

            setupEditText(item, false)

            itemView.clicks()
                .subscribe {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        itemView.et_amount.requestFocus()
                        currencyRatesView.onItemClicked(adapterPosition)
                        selectedPosition = SELECTED_ITEM_POSITION
                    }
                }
        }

        private fun setupEditText(item: CurrencyRateVM, payloadCall: Boolean) {
            itemView.et_amount.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    itemView.et_amount.addTextChangedListener(textWatcher)
                    if (selectedPosition != adapterPosition) {
                        selectedPosition = adapterPosition
                        currencyRatesView.onItemFocused(adapterPosition)
                    }
                } else {
                    itemView.et_amount.removeTextChangedListener(textWatcher)
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
            if (selectedPosition == adapterPosition) {
                itemView.et_amount.removeTextChangedListener(textWatcher)
                selectedSelectionPosition = itemView.et_amount.selectionStart
                if (!payloadCall) {
                    itemView.et_amount.setText(item.amount)
                }
                itemView.et_amount.setSelection(selectedSelectionPosition)
                itemView.et_amount.requestFocus()
                itemView.et_amount.addTextChangedListener(textWatcher)
            } else {
                itemView.et_amount.setText(item.amount)
            }
        }

        override fun bindPayloads(item: CurrencyRateVM, payload: Any) {
            when (payload) {
                is CurrencyRatesPayload.Amount -> {
                    setupEditText(item, true)
                }
                else -> {
                    bind(item)
                }
            }
        }
    }
}