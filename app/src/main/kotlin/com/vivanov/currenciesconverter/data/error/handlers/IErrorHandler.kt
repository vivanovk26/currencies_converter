package com.vivanov.currenciesconverter.data.error.handlers

interface IErrorHandler {

    fun handleError(throwable: Throwable)
}