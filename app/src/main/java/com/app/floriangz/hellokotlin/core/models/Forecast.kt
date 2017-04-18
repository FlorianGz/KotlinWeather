package com.app.floriangz.hellokotlin.core.models

import android.util.Log
import com.app.floriangz.hellokotlin.R
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Forecast: RealmObject(){
    @PrimaryKey
    open var id:Long = 0
    open var daily: DailyForecast? = null
}

open class DailyForecast: RealmObject() {
    open var data: RealmList<DailyData>? = null
}

open class DailyData: RealmObject() {
    open var summary: String? = null
    open var icon: String? = null
    open var temperatureMin: Double? = null
    open var temperatureMax: Double? = null
    open var time: Long? = null

    fun getIcon() : Int {
        Log.d("testing", "icon received = " + icon)
        when(icon) {
            "clear-day" -> return  R.drawable.clear_day
            "rain" -> return  R.drawable.rain
            "partly-cloudy-day" -> return  R.drawable.partly_cloudy_day
            "partly-cloudy-night" -> return  R.drawable.partly_cloudy_night
            else -> return 0
        }
    }
}