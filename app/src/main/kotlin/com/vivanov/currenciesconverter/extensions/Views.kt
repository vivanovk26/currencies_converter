package com.vivanov.currenciesconverter.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    inputMethodManager.showSoftInput(this, 0)
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
        Timber.e("HideKeyboard: ${ignored.message}")
    }
    return false
}

private const val CLICK_DELAY: Long = 500L

fun Observable<Unit>.preventDoubleClick(): Observable<Unit> {
    return this.throttleFirst(CLICK_DELAY, TimeUnit.MILLISECONDS)
}