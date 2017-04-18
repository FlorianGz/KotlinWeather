package com.app.floriangz.hellokotlin.feature.forecast

import com.app.floriangz.hellokotlin.core.models.Forecast
import io.reactivex.Observable

interface ForecastView {
    fun updateForecast(forecast: Forecast)
    fun showErrorView(message: String?)
    fun observeLocationPermission() : Observable<Boolean>
}
