package com.example.weather_forecast_test_repository.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitBuilder() {
    inline fun <reified T>build (): T{
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build().create(T::class.java)
    }
}