package com.example.weather_forecast_test_repository.usecase

import com.example.weather_forecast_test_repository.api.WeatherRepository
import com.example.weather_forecast_test_repository.dataclass.WeatherResult

interface ForecastUseCase {
    suspend fun execute(city: String): UseCaseResult<WeatherResult>
}

class ForecastUseCaseImplement (
    val remoteDataSource: WeatherRepository
): ForecastUseCase {
    override suspend fun execute(city: String): UseCaseResult<WeatherResult> {
        return try {
            val result = remoteDataSource.forecast(city = city)
            UseCaseResult.Success(result)
        } catch (ex:Exception) {
            UseCaseResult.Error(ex)
        }
    }

}