package com.example.weather_forecast_test_repository.dataclass

import kotlin.math.roundToInt

data class WeatherResult(
    val message: String,
    val cod: String,
    val count: Int,
    val list: List<WeatherData>
)

data class WeatherData(
    val id: String,
    val name: String,
    val coord: MapPosition,
    val main: WeatherMainStat,
    val dt: Long
)

data class MapPosition(
    val lat: String,
    val lon: String
)

data class WeatherMainStat(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double,
)