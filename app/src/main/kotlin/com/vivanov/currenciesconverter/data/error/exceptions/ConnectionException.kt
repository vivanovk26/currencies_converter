package com.vivanov.currenciesconverter.data.error.exceptions

import android.support.annotation.StringRes
import com.example.currenciesconverter.R

class ConnectionException(
    @StringRes
    var errorMessage: Int = R.string.error_connection
) : Throwable()