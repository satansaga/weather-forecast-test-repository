package com.example.weather_forecast_test_repository.presentation.weathernow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weather_forecast_test_repository.databinding.ActivityWeatherNowBinding


class WeatherNowActivity: AppCompatActivity() {
    companion object {
        private const val KEY_CITY_NAME = "city"
        fun getPage(context: Context, city: String): Intent{
            val intent =  Intent(context, WeatherNowActivity::class.java)
            intent.putExtra(KEY_CITY_NAME, city)
            return intent
        }
    }
    private lateinit var intentCity: String
    private lateinit var binding: ActivityWeatherNowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherNowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtraParams()
        setView()
        setViewAction()
    }
    private fun getExtraParams() {
        intentCity = intent.getStringExtra(KEY_CITY_NAME)?:""
    }
    private fun setView(){
        binding.searchBar.layoutSearchEditText.setText(intentCity)
        binding.searchBar.layoutSearch.clearFocus()
    }
    private fun setViewAction(){

    }
}