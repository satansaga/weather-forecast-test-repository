package com.example.weather_forecast_test_repository.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import com.example.weather_forecast_test_repository.databinding.ActivityMainBinding
import com.example.weather_forecast_test_repository.presentation.weathernow.WeatherNowActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewAction()
    }

    private fun setViewAction () {
        binding.buttonSearch.setOnClickListener {
            val city = binding.layoutTextEdit.text.toString()
            val intent = WeatherNowActivity.getPage(this, city)
            startActivity(intent)
        }
    }

}