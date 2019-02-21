package com.vivanov.currenciesconverter.presentation.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.currenciesconverter.R
import com.vivanov.currenciesconverter.config.di.CURRENCY_RATES_SCOPE
import com.vivanov.currenciesconverter.domain.contracts.ICurrencyRatesContract
import com.vivanov.currenciesconverter.extensions.gone
import com.vivanov.currenciesconverter.extensions.visible
import com.vivanov.currenciesconverter.presentation.core.views.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ext.android.getViewModel
import java.math.BigDecimal

class CurrencyRatesActivity :
    BaseActivity<ICurrencyRatesContract.ICurrencyRatesViewModel, CurrencyRatesState>(),
    ICurrencyRatesContract.ICurrencyRatesView {

    override val layoutId: Int = R.layout.activity_main

    private val currencyRatesAdapter: CurrencyRatesAdapter = CurrencyRatesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.CurrenciesConverterTheme)

        super.onCreate(savedInstanceState)
    }

    override fun setupViewModel() {
        getKoin().getOrCreateScope(CURRENCY_RATES_SCOPE)
        viewModel = getViewModel<CurrencyRatesViewModel>()
    }

    override fun setupUI() {
        super.setupUI()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rv.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        rv.layoutManager = layoutManager
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.currency_rate_divider)?.let {
            divider.setDrawable(it)
        }
        rv.addItemDecoration(divider)
        rv.adapter = currencyRatesAdapter
        currencyRatesAdapter.currencyRatesView = this@CurrencyRatesActivity
    }

    override fun onItemClicked(position: Int) {
        viewModel.eventsSubject.onNext(CurrencyRatesEvent.ItemClickedEvent(position))
    }

    override fun onItemFocused(position: Int) {
        viewModel.eventsSubject.onNext(
            CurrencyRatesEvent.AmountChangedEvent(
                position, BigDecimal.TEN
            )
        )
    }

    override fun subscribeOnStateChanges(state: CurrencyRatesState) {
        registerNonNullObserver(state.loading) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        registerNonNullObserver(state.currencyRateVMs) {
            currencyRatesAdapter.setList(it)
        }
        registerNonNullObserver(state.emptyViewVisible) {
            if (it) {
                rv.gone()
                tv_empty_view.gone()
            } else {
                rv.visible()
                tv_empty_view.gone()
            }
        }
        registerNullableObserver(state.error, Observer {
            // Empty.
        })
    }

    override fun showLoading() {
        pb.visible()
    }

    override fun hideLoading() {
        pb.gone()
    }
}