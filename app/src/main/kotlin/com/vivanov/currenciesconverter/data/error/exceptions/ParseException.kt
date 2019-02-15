package com.vivanov.currenciesconverter.data.error.exceptions

import android.support.annotation.StringRes
import com.example.currenciesconverter.R

class ParseException(
    @StringRes
    var errorMessage: Int = R.string.error_invalid_server_data
) : Throwable()