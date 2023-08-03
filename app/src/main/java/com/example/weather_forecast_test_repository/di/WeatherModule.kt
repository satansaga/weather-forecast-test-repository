package com.example.weather_forecast_test_repository.di

import com.example.weather_forecast_test_repository.api.WeatherRepository
import com.example.weather_forecast_test_repository.usecase.WeatherUseCase
import com.example.weather_forecast_test_repository.usecase.WeatherUseCaseImplement
import org.koin.core.module.Module
import org.koin.dsl.module

val weatherModule = module {
    factory<WeatherUseCase> {
        WeatherUseCaseImplement(
            remoteDataSource = get()
        )
    }
}