package com.vivanov.currenciesconverter.presentation.core.views

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.vivanov.currenciesconverter.extensions.nonNullObserve
import com.vivanov.currenciesconverter.presentation.core.states.IState
import com.vivanov.currenciesconverter.presentation.core.viewmodels.IBaseViewModel

abstract class BaseActivity<IViewModel : IBaseViewModel<State, *, *>, State : IState>
    : AppCompatActivity(), IBaseView {

    protected lateinit var viewModel: IViewModel
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
        setupLifeCycle()
        setupUI()
        subscribeOnStateChanges(viewModel.state)
    }

    protected abstract fun setupViewModel()

    private fun setupLifeCycle() {
        viewModel.setupLifecycle(lifecycle)
    }

    @CallSuper
    protected open fun setupUI() {
        setContentView(layoutId)
    }

    protected open fun subscribeOnStateChanges(state: State) {
        // Empty.
    }

    protected fun <T> registerNonNullObserver(liveData: LiveData<T>, func: (t: T) -> Unit) {
        liveData.nonNullObserve(this, func)
    }

    protected fun <T> registerNullableObserver(liveData: LiveData<T>, observer: Observer<T>) {
        liveData.observe(this, observer)
    }

    override fun showLoading() {
        // Empty.
    }

    override fun hideLoading() {
        // Empty.
    }
}
