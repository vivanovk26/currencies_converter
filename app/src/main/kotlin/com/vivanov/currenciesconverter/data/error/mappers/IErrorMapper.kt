package com.vivanov.currenciesconverter.data.error.mappers

interface IErrorMapper {

    fun map(throwable: Throwable): Throwable
}