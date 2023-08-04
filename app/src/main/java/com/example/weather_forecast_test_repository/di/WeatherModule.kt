package com.example.weather_forecast_test_repository.di

import com.example.weather_forecast_test_repository.api.RetrofitBuilder
import com.example.weather_forecast_test_repository.api.WeatherRepository
import com.example.weather_forecast_test_repository.presentation.forecast.ForecastViewModel
import com.example.weather_forecast_test_repository.presentation.weather.WeatherViewModel
import com.example.weather_forecast_test_repository.usecase.ForecastUseCase
import com.example.weather_forecast_test_repository.usecase.ForecastUseCaseImplement
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

    factory<ForecastUseCase> {
        ForecastUseCaseImplement(
            remoteDataSource = get()
        )
    }

    single <WeatherRepository> {
        RetrofitBuilder().build()
    }

    viewModel {
        WeatherViewModel(
            weatherUseCase = get()
        )
    }

    viewModel {
        ForecastViewModel(
            forecastUseCase = get()
        )
    }
}