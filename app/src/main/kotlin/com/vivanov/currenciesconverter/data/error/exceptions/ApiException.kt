package com.vivanov.currenciesconverter.data.error.exceptions

class ApiException(
    val errorCode: Int? = null,
    val errorMessage: String
) : Throwable()