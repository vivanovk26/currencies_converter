package com.vivanov.currenciesconverter.presentation.core.viewmodels

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import com.vivanov.currenciesconverter.presentation.core.actions.IAction
import com.vivanov.currenciesconverter.presentation.core.actions.IRefreshableAction
import com.vivanov.currenciesconverter.presentation.core.events.IEvent
import com.vivanov.currenciesconverter.presentation.core.states.IState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.koin.standalone.KoinComponent

abstract class BaseViewModel<State : IState, Event : IEvent, Action : IAction> : ViewModel(),
    IBaseViewModel<State, Event, Action>, KoinComponent {

    final override val eventsSubject: PublishSubject<Event> = PublishSubject.create()
    override var actionsSubject: PublishSubject<Action> = PublishSubject.create()
        set(value) {
            field = value
            actionsDisposable.add(
                value.subscribe {
                    reduce(it)
                }
            )
        }
    private val actionsDisposable: CompositeDisposable = CompositeDisposable()
    private var lastAction: Action? = null

    init {
        actionsDisposable.add(
            eventsSubject.subscribe {
                onEventChanged(it)
            }
        )
    }

    @CallSuper
    protected fun reduce(action: Action) {
        if (action != lastAction || action is IRefreshableAction) {
            lastAction = action
            onActionChanged(action)
        }
    }

    override fun setupLifecycle(lifecycle: Lifecycle) {
        // Empty.
    }

    protected open fun onEventChanged(event: Event) {
        // Empty.
    }

    protected open fun onActionChanged(action: Action) {
        // Empty.
    }

    override fun onCleared() {
        actionsDisposable.clear()
        scope.close()

        super.onCleared()
    }
}