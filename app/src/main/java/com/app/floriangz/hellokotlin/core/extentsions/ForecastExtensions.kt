package com.app.floriangz.hellokotlin.core.extentsions

fun Double.fahrenheitToCelsius(): String {
    val celsius = (this - 32) / 1.8
    return "${celsius.toInt()}°"
}
