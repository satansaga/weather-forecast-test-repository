package com.example.weather_forecast_test_repository.api

import com.example.weather_forecast_test_repository.dataclass.WeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRepository {
    @GET("/data/2.5/find")
    suspend fun weather(
        @Query("q") city: String = "Bangkok",
        @Query("appid") appid: String = "2430553deb5ac8dc35fc46382f719cee"
    ): WeatherResult
}