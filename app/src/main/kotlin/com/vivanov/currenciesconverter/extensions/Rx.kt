package com.vivanov.currenciesconverter.extensions

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IRxSchedulers {

    fun io(): Scheduler

    fun main(): Scheduler
}

object RxSchedulers : IRxSchedulers {

    override fun io(): Scheduler = Schedulers.io()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}

object RxSchedulersTest : IRxSchedulers {

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun main(): Scheduler = Schedulers.trampoline()
}

fun <T> Observable<T>.async(rxSchedulers: IRxSchedulers): Observable<T> =
    subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.main())

fun <T> Single<T>.async(rxSchedulers: IRxSchedulers): Single<T> =
    subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.main())

fun Completable.async(rxSchedulers: IRxSchedulers): Completable =
    subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.main())

fun <T> Observable<T>.mainThread(rxSchedulers: IRxSchedulers): Observable<T> =
    subscribeOn(rxSchedulers.main()).observeOn(rxSchedulers.main())

fun <T> Single<T>.mainThread(rxSchedulers: IRxSchedulers): Single<T> =
    subscribeOn(rxSchedulers.main()).observeOn(rxSchedulers.main())

fun Completable.mainThread(rxSchedulers: IRxSchedulers): Completable =
    subscribeOn(rxSchedulers.main()).observeOn(rxSchedulers.main())