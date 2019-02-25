package com.vivanov.currenciesconverter.config.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vivanov.currenciesconverter.BuildConfig
import com.vivanov.currenciesconverter.data.error.mappers.ErrorMapper
import com.vivanov.currenciesconverter.data.error.mappers.IErrorMapper
import com.vivanov.currenciesconverter.data.network.api.CurrenciesConverterApi
import com.vivanov.currenciesconverter.data.network.mappers.ApiMapper
import com.vivanov.currenciesconverter.data.network.mappers.IApiMapper
import com.vivanov.currenciesconverter.data.network.services.ApiService
import com.vivanov.currenciesconverter.data.network.services.IApiService
import com.vivanov.currenciesconverter.data.network.services.IImageLoaderService
import com.vivanov.currenciesconverter.data.network.services.ImageLoaderService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 30
private const val READ_WRITE_TIMEOUT = 30
private const val ENDPOINT = "https://revolut.duckdns.org/"

val networkModule: Module = module {

    single<CurrenciesConverterApi> {
        get<Retrofit>().create<CurrenciesConverterApi>(
            CurrenciesConverterApi::class.java
        )
    }
    single<Retrofit> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .baseUrl(ENDPOINT)
            .client(get())
            .build()
    }
    single<OkHttpClient> {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }

        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(READ_WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)

        okHttpClientBuilder.build()
    }

    single<Gson> {
        GsonBuilder().create()
    }
    single<IApiService> {
        ApiService(get(), get(), get())
    }
    single<IApiMapper> {
        ApiMapper()
    }
    single<IImageLoaderService> {
        ImageLoaderService(get(), get())
    }
    single<IErrorMapper> {
        ErrorMapper(get())
    }
}