package com.vivanov.currencyconverter.data.error.mappers

import com.vivanov.currenciesconverter.data.error.exceptions.ApiException
import com.vivanov.currenciesconverter.data.error.exceptions.ConnectionException
import com.vivanov.currenciesconverter.data.error.mappers.ErrorMapper
import com.vivanov.currenciesconverter.data.providers.IResourcesProvider
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

class ErrorMapperTestCases : Spek({

    describe("ApiMapper tests") {

        val resourcesProvider = object : IResourcesProvider {

            override fun getString(resId: Int): String {
                return "testText"
            }

            override fun getString(resId: Int, vararg args: Any): String {
                return ""
            }
        }
        val errorMapper = ErrorMapper(resourcesProvider)

        describe("map throwable") {

            it("check HttpException to ApiException with text map") {
                val httpException = HttpException(
                    Response.error<Int>(
                        422, ResponseBody.create(MediaType.get("application/json"), "")
                    )
                )

                Assertions.assertEquals(
                    (errorMapper.map(httpException) as ApiException).errorMessage,
                    "testText"
                )
            }

            it("check UnknownHostException to ConnectionException map") {
                Assertions.assertTrue(
                    errorMapper.map(UnknownHostException()) is ConnectionException
                )
            }

            it("check others exceptions stay the same") {
                Assertions.assertTrue(
                    errorMapper.map(IOException()) is IOException
                )
            }
        }
    }
})