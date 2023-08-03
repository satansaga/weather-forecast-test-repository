package com.example.weather_forecast_test_repository.usecase

import com.example.weather_forecast_test_repository.api.WeatherRepository
import com.example.weather_forecast_test_repository.dataclass.WeatherResult

interface WeatherUseCase {
    fun execute(city: String): UseCaseResult<WeatherResult>
}

class WeatherUseCaseImplement(
    val remoteDataSource: WeatherRepository
): WeatherUseCase {
    override fun execute(city: String): UseCaseResult<WeatherResult> {
        TODO("Not yet implemented")
    }
}