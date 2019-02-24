package com.vivanov.currenciesconverter.data.error.renderers

import android.support.v7.app.AppCompatActivity
import com.example.currenciesconverter.R
import com.vivanov.currenciesconverter.data.error.exceptions.ApiException
import com.vivanov.currenciesconverter.data.error.exceptions.ConnectionException
import com.vivanov.currenciesconverter.data.error.exceptions.ParseException
import com.vivanov.currenciesconverter.presentation.core.dialogs.ConnectionErrorDialogFragment
import com.vivanov.currenciesconverter.presentation.core.dialogs.InfoDialogFragment

private const val ERROR_DIALOG_TAG: String = "ERROR_DIALOG_TAG"

fun AppCompatActivity.showError(error: Throwable) {

    val errorDialogFragment = when (error) {
        is ApiException -> InfoDialogFragment.createInstance(
            getString(R.string.error), error.errorMessage
        )
        is ConnectionException -> ConnectionErrorDialogFragment()
        is ParseException -> InfoDialogFragment.createInstance(
            getString(R.string.error), getString(R.string.error_invalid_server_data)
        )
        else -> InfoDialogFragment.createInstance(
            getString(R.string.error), getString(R.string.error_unexpected)
        )
    }
    errorDialogFragment.isCancelable = false
    errorDialogFragment.show(
        supportFragmentManager,
        ERROR_DIALOG_TAG
    )
}