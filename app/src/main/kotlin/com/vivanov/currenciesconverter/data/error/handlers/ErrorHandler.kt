package com.vivanov.currenciesconverter.data.error.handlers

import com.vivanov.currenciesconverter.data.error.exceptions.ApiException
import com.vivanov.currenciesconverter.data.error.exceptions.ParseException
import retrofit2.HttpException
import java.net.UnknownHostException

class ErrorHandler : IErrorHandler {

    override fun handleError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                // navigator.goForward(ConnectionErrorDialogScreen)
            }
            is UnknownHostException -> {
                // navigator.goForward(ConnectionErrorDialogScreen)
            }
            is ApiException -> {
                /*navigator.goForward(
                    InfoDialogScreen(
                        titleRes = R.string.error,
                        message = throwable.errorMessage,
                        buttonTextRes = R.string.b_ok
                    )
                )*/
            }
            is ParseException -> {
                /*navigator.goForward(
                    InfoDialogScreen(
                        titleRes = R.string.error,
                        messageRes = R.string.error_invalid_server_data,
                        buttonTextRes = R.string.b_ok
                    )
                )*/
            }
            else -> {
            }/*navigator.goForward(
                InfoDialogScreen(
                    titleRes = R.string.error,
                    messageRes = R.string.error_unexpected,
                    buttonTextRes = R.string.b_ok
                )
            )*/
        }
    }
}