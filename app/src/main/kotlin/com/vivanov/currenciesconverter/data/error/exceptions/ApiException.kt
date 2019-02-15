package com.vivanov.currenciesconverter.data.error.exceptions

class ApiException(
    val errorCode: Int,
    val errorMessage: String
) : Throwable()