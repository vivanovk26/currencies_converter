package com.vivanov.currenciesconverter.presentation.core.viewmodels

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import com.vivanov.currenciesconverter.presentation.core.actions.IAction
import com.vivanov.currenciesconverter.presentation.core.events.IEvent
import com.vivanov.currenciesconverter.presentation.core.states.IState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.koin.core.scope.Scope
import org.koin.standalone.KoinComponent
import timber.log.Timber

abstract class BaseViewModel<State : IState, Event : IEvent, Action : IAction> : ViewModel(),
    IBaseViewModel<State, Event, Action>, KoinComponent {

    final override val eventsSubject: PublishSubject<Event> = PublishSubject.create()
    protected var actionsSubject: PublishSubject<Action> = PublishSubject.create()
        set(value) {
            field = value
            compositeDisposable.add(
                value.subscribe {
                    reduceAction(it)
                }
            )
        }
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected abstract val scope: Scope

    init {
        compositeDisposable.add(
            eventsSubject.subscribe {
                reduceEvent(it)
            }
        )
    }

    @CallSuper
    protected fun reduceAction(action: Action) {
        Timber.d("Action: $action")
        onActionChanged(action)
    }

    @CallSuper
    protected fun reduceEvent(event: Event) {
        Timber.d("Event: $event")
        onEventChanged(event)
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
        compositeDisposable.clear()
        scope.close()

        super.onCleared()
    }
}