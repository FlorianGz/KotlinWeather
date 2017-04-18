package com.app.floriangz.hellokotlin.feature.forecast

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.app.floriangz.hellokotlin.R
import com.app.floriangz.hellokotlin.core.models.Forecast
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import kotlinx.android.synthetic.main.content_tasks_list.*

class ForecastActivity : AppCompatActivity(), ForecastView {

    val forecastPresenter = ForecastPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks_list)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        forecastList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        forecastPresenter.resume()
    }

    override fun onPause() {
        super.onPause()
        forecastPresenter.pause()
    }

    override fun observeLocationPermission(): Observable<Boolean> {
        return RxPermissions(this).request(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun updateForecast(forecast: Forecast) {
        forecastList.adapter = ForecastAdapter(forecast)
    }

    override fun showErrorView(message: String?) {
        Toast.makeText(this, "error : $message", Toast.LENGTH_LONG).show()
    }
}
