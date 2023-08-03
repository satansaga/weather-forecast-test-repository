package com.example.weather_forecast_test_repository.usecase

import com.example.weather_forecast_test_repository.api.WeatherRepository
import com.example.weather_forecast_test_repository.dataclass.WeatherResult

interface WeatherUseCase {
    suspend fun execute(city: String): UseCaseResult<WeatherResult>
}

class WeatherUseCaseImplement(
    val remoteDataSource: WeatherRepository
): WeatherUseCase {
    override suspend fun execute(city: String): UseCaseResult<WeatherResult> {
        return try {
            val result = remoteDataSource.weather(city = city)
            UseCaseResult.Success(result)
        } catch(ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}