package com.app.floriangz.hellokotlin.core.store

import com.app.floriangz.hellokotlin.core.models.Forecast
import io.realm.Realm

class ForecastStore {

    val realm:Realm = Realm.getDefaultInstance()

    fun saveForecast(forecast: Forecast) {
        forecast.id = realm.where(Forecast::class.java).max("id").toLong() + 1
        realm.executeTransaction {
            realm.insert(forecast)
        }
    }
}
