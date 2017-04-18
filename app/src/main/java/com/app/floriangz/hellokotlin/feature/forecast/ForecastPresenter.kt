package com.app.floriangz.hellokotlin.feature.forecast

import com.app.floriangz.hellokotlin.HelloKotlinApp
import com.app.floriangz.hellokotlin.core.api.RetrofitWrapper
import com.app.floriangz.hellokotlin.core.store.ForecastStore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ForecastPresenter(val forecastView: ForecastView) {

    val retrofitWrapper = RetrofitWrapper()
    val forecastStore = ForecastStore()
    val compositeDisposable = CompositeDisposable()

    fun resume() {
        loadForecast()
    }

    fun pause() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun loadForecast() {
        val disposable = forecastView.observeLocationPermission()
                .doOnNext { granted -> if (!granted) throw Exception("permission denied") }
                .flatMapMaybe { _ -> HelloKotlinApp.getUserLocation() }
                .observeOn(Schedulers.io())
                .flatMap { location -> retrofitWrapper.forecastApi.getForecast(location.latitude, location.longitude) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecast ->
                    forecastStore.saveForecast(forecast)
                    forecastView.updateForecast(forecast)
                }, { error ->
                    forecastView.showErrorView(error.message)
                    error.printStackTrace()
                })

        compositeDisposable.add(disposable)
    }
}
