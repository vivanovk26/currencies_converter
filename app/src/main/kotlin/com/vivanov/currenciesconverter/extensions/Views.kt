package com.vivanov.currenciesconverter.extensions

import android.app.Activity
import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.support.v4.app.SharedElementCallback
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
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

fun View.setTransitionName(id: String, @StringRes textRes: Int) {
    ViewCompat.setTransitionName(this, "$id${this.context.getString(textRes)}")
}

fun View.getSharedElement(): Pair<View, String> {
    return Pair(this, ViewCompat.getTransitionName(this)!!)
}

fun TextView.setDrawableSpannable(@DrawableRes drawableRes: Int, text: String) {
    val drawable = ContextCompat.getDrawable(context, drawableRes)
    if (drawable == null) {
        setText(text)
    } else {
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val imageSpan = ImageSpan(drawable, ImageSpan.ALIGN_BASELINE)
        val spannableString = SpannableString("  $text")
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        setText(spannableString)
    }
}

fun Activity.sharedAnimationImageFix() {
    ActivityCompat.setExitSharedElementCallback(this, object : SharedElementCallback() {

        override fun onSharedElementEnd(
            sharedElementNames: MutableList<String>?,
            sharedElements: MutableList<View>?,
            sharedElementSnapshots: MutableList<View>?
        ) {
            super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
            sharedElements?.let {
                for (view in it) {
                    if (view is SimpleDraweeView) {
                        view.drawable.setVisible(true, true)
                    }
                }
            }
        }
    })
}

private const val CLICK_DELAY: Long = 500L

fun Observable<Unit>.preventDoubleClick(): Observable<Unit> {
    return this.throttleFirst(CLICK_DELAY, TimeUnit.MILLISECONDS)
}