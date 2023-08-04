package com.example.weather_forecast_test_repository.presentation.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather_forecast_test_repository.dataclass.CityData
import com.example.weather_forecast_test_repository.dataclass.WeatherData
import com.example.weather_forecast_test_repository.usecase.ForecastUseCase
import com.example.weather_forecast_test_repository.usecase.UseCaseResult
import com.example.weather_forecast_test_repository.usecase.WeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ForecastViewModel(
    val forecastUseCase: ForecastUseCase
): ViewModel(), CoroutineScope {
    override val coroutineContext = Dispatchers.IO

    val forecastData = MutableLiveData<List<WeatherData>>()
    val cityData = MutableLiveData<String>()

    fun getForecast (city: String) {
        launch {
            val result = forecastUseCase.execute(city)
            if(result is UseCaseResult.Success){
                if(result.data != null && result.data.list.isEmpty().not()){
                    result.data?.let {
                        forecastData.postValue(
                            it.list
                        )
                        cityData.postValue(
                            it.city.name
                        )
                    }
                }
            }
        }
    }
}