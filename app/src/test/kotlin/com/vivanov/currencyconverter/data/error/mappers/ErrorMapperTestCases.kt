package com.vivanov.currencyconverter.data.error.mappers

import com.vivanov.currenciesconverter.data.error.exceptions.ConnectionException
import com.vivanov.currenciesconverter.data.error.mappers.ErrorMapper
import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.IOException
import java.net.UnknownHostException

class ErrorMapperTestCases : Spek({

    describe("ApiMapper tests") {

        val errorMapper = ErrorMapper()

        describe("map throwable") {

            it("check UnknownHostException to Connection exception map") {
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