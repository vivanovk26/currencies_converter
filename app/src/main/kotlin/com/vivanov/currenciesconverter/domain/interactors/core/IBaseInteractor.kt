package com.vivanov.currenciesconverter.domain.interactors.core

import com.vivanov.currenciesconverter.presentation.core.actions.IAction
import io.reactivex.subjects.PublishSubject

interface IBaseInteractor<Action : IAction> {

    val actionsSubject: PublishSubject<Action>
}