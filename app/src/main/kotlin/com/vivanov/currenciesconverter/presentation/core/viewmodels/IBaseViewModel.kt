package com.vivanov.currenciesconverter.presentation.core.viewmodels

import android.arch.lifecycle.Lifecycle
import com.vivanov.currenciesconverter.presentation.core.actions.IAction
import com.vivanov.currenciesconverter.presentation.core.events.IEvent
import com.vivanov.currenciesconverter.presentation.core.states.IState
import io.reactivex.subjects.PublishSubject

interface IBaseViewModel<State : IState, Event : IEvent, Action : IAction> {

    val eventsSubject: PublishSubject<Event>
    val state: State

    fun setupLifecycle(lifecycle: Lifecycle)
}
