package com.app.floriangz.hellokotlin.core.api

import com.app.floriangz.hellokotlin.core.models.Forecast
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ForecastApi {
    @GET("{lat},{lng}?exclude=[hourly,minutely,currently,flags,alerts]")
    fun getForecast(@Path("lat") lat:Double, @Path("lng") lng:Double) : Observable<Forecast>
}
