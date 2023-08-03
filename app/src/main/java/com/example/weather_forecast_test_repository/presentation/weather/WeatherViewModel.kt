package com.example.weather_forecast_test_repository.presentation.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather_forecast_test_repository.dataclass.WeatherMainStat
import com.example.weather_forecast_test_repository.dataclass.WeatherResult
import com.example.weather_forecast_test_repository.usecase.UseCaseResult
import com.example.weather_forecast_test_repository.usecase.WeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(
    val weatherUseCase: WeatherUseCase
): ViewModel(), CoroutineScope {
    override val coroutineContext = Dispatchers.IO

    val weatherData = MutableLiveData<WeatherMainStat>()
    val cityName = MutableLiveData<String>()
    val time = MutableLiveData<Long>()

    fun getWeather (city: String) {
        launch {
            val result = weatherUseCase.execute(city)
            if (result is UseCaseResult.Success){
                if(result.data != null && result.data.list.isEmpty().not()){
                    result.data.list.first().let {
                        weatherData.postValue(it.main)
                        cityName.postValue(it.name)
                        time.postValue(it.dt)
                    }
                }
            }
        }
    }
}