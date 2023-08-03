package com.example.weather_forecast_test_repository.di

import com.example.weather_forecast_test_repository.api.RetrofitBuilder
import com.example.weather_forecast_test_repository.api.WeatherRepository
import com.example.weather_forecast_test_repository.presentation.weather.WeatherViewModel
import com.example.weather_forecast_test_repository.usecase.WeatherUseCase
import com.example.weather_forecast_test_repository.usecase.WeatherUseCaseImplement
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    factory<WeatherUseCase> {
        WeatherUseCaseImplement(
            remoteDataSource = get()
        )
    }

    single <WeatherRepository> {
        RetrofitBuilder().build()
    }
}