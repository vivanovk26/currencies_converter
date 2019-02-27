package com.vivanov.currenciesconverter.data.error.mappers

import com.vivanov.currenciesconverter.R
import com.vivanov.currenciesconverter.data.error.exceptions.ApiException
import com.vivanov.currenciesconverter.data.error.exceptions.ConnectionException
import com.vivanov.currenciesconverter.data.providers.IResourcesProvider
import retrofit2.HttpException
import java.net.UnknownHostException

private const val HTTP_CODE_422: Int = 422

class ErrorMapper(
    private val resourcesProvider: IResourcesProvider
) : IErrorMapper {

    override fun map(throwable: Throwable): Throwable {
        return when (throwable) {
            is HttpException -> {
                mapHttpException(throwable)
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

    private fun mapHttpException(httpException: HttpException): Throwable {
        return when (httpException.code()) {
            HTTP_CODE_422 -> {
                ApiException(
                    HTTP_CODE_422,
                    resourcesProvider.getString(R.string.error_invalid_base_currency)
                )
            }
            else -> ConnectionException()
        }
    }
}