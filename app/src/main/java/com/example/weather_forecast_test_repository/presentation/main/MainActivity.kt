package com.example.weather_forecast_test_repository.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather_forecast_test_repository.R
import com.example.weather_forecast_test_repository.databinding.ActivityMainBinding
import com.example.weather_forecast_test_repository.presentation.weather.WeatherActivity

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
            if(city.isEmpty()){
                binding.layoutTextInput.error = getString(R.string.error_blank_city_name)
            } else {
                binding.layoutTextInput.error = ""
                val intent = WeatherActivity.getPage(this, city)
                startActivity(intent)
            }
        }
    }

}