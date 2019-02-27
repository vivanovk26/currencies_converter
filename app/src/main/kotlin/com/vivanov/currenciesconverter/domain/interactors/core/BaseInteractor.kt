package com.vivanov.currenciesconverter.domain.interactors.core

import com.vivanov.currenciesconverter.presentation.core.actions.IAction
import io.reactivex.subjects.PublishSubject

abstract class BaseInteractor<Action : IAction> : IBaseInteractor<Action> {

    override val actionsSubject: PublishSubject<Action> = PublishSubject.create()
}