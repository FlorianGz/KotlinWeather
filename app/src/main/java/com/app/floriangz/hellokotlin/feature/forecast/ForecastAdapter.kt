package com.app.floriangz.hellokotlin.feature.forecast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.floriangz.hellokotlin.R
import com.app.floriangz.hellokotlin.core.extentsions.fahrenheitToCelsius
import com.app.floriangz.hellokotlin.core.models.DailyData
import com.app.floriangz.hellokotlin.core.models.Forecast
import kotlinx.android.synthetic.main.forecast_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(var forecast: Forecast): RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return forecast.daily?.data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.forecast_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val dailyDataItem = forecast.daily?.data?.get(position)
        holder?.bind(dailyDataItem)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var icon: ImageView = view.forecast_icon
        var dateView: TextView = view.date
        var summaryView: TextView = view.forecast_summary
        var temperatureView: TextView = view.forecast_temperature

        fun bind(item: DailyData?) {
            summaryView.text = item?.summary
            val temperature = "${item?.temperatureMin?.fahrenheitToCelsius()} - ${item?.temperatureMax?.fahrenheitToCelsius()}"
            temperatureView.text = temperature
            icon.setImageResource(item?.getIcon() ?: 0)
            val df = SimpleDateFormat("EE dd MMM", Locale.getDefault())
            val time = item?.time ?: 0
            val date = df.format(time * 1000L)
            dateView.text = date
        }
    }
}