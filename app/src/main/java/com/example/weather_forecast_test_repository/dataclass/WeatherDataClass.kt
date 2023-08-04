package com.example.weather_forecast_test_repository.dataclass

import kotlin.math.roundToInt

data class WeatherResult(
    val message: String,
    val cod: String,
    val list: List<WeatherData>,
    val city: CityData
)

data class WeatherData(
    val id: String,
    val name: String,
    val main: WeatherMainStat,
    val dt: Long,
    val dt_txt: String
)

data class WeatherMainStat(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double,
)

data class CityData(
    val id: String,
    val name: String
)