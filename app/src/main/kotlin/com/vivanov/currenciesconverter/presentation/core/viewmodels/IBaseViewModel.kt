package com.vivanov.currenciesconverter.presentation.core.viewmodels

import android.arch.lifecycle.Lifecycle
import com.vivanov.currenciesconverter.presentation.core.actions.IAction
import com.vivanov.currenciesconverter.presentation.core.events.IEvent
import com.vivanov.currenciesconverter.presentation.core.states.IState
import io.reactivex.subjects.PublishSubject
import org.koin.core.scope.Scope

interface IBaseViewModel<State : IState, Event : IEvent, Action : IAction> {

    val eventsSubject: PublishSubject<Event>
    var actionsSubject: PublishSubject<Action>
    val scope: Scope
    val state: State

    fun setupLifecycle(lifecycle: Lifecycle)
}
