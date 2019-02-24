package com.vivanov.currenciesconverter.data.error.mappers

import com.vivanov.currenciesconverter.data.error.exceptions.ConnectionException
import retrofit2.HttpException
import java.net.UnknownHostException

class ErrorMapper : IErrorMapper {

    override fun map(throwable: Throwable): Throwable {
        return when (throwable) {
            is HttpException -> {
                ConnectionException()
            }
            is UnknownHostException -> {
                ConnectionException()
            }
            // ApiException, ParseException don't need to be mapped
            else -> {
                throwable
            }
        }
    }
}