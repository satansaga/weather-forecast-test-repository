package com.example.weather_forecast_test_repository

import android.content.Context
import kotlin.math.roundToInt

const val KELVIN_DIFF: Double = 273.15

public inline fun Double.toCelsiusText (): String {
    return this.minus(KELVIN_DIFF).roundToInt().toString()
}

public inline fun Double.toFahrenheitText (): String {
    val tempF: Double = (1.80 * (this - KELVIN_DIFF)) + 32.00
    return tempF.roundToInt().toString()
}

public inline fun Double.getTemperatureText (context: Context, boolean: Boolean): String {
    return if (boolean){
        context.getString(
            R.string.text_temperature,
            this.toFahrenheitText(),
            "F"
        )
    } else {
        context.getString(
            R.string.text_temperature,
            this.toCelsiusText(),
            "C"
        )
    }
}