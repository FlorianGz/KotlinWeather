package com.app.floriangz.hellokotlin.core.api

import com.app.floriangz.hellokotlin.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitWrapper {
    val forecastApi: ForecastApi

    init {
        val baseUrl = "https://api.darksky.net/forecast/" + BuildConfig.FORECAST_API_KEY + "/"
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        forecastApi = retrofit.create(ForecastApi::class.java)
    }
}
