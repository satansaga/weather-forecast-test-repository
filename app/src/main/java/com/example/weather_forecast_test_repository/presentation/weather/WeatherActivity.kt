package com.example.weather_forecast_test_repository.presentation.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.weather_forecast_test_repository.databinding.ActivityWeatherNowBinding
import androidx.lifecycle.Observer
import com.example.weather_forecast_test_repository.dataclass.WeatherResult

class WeatherActivity: AppCompatActivity() {
    companion object {
        private const val KEY_CITY_NAME = "city"
        fun getPage(context: Context, city: String): Intent{
            val intent =  Intent(context, WeatherActivity::class.java)
            intent.putExtra(KEY_CITY_NAME, city)
            return intent
        }
    }
    private val viewModel: WeatherViewModel by viewModel()
    private lateinit var intentCity: String
    private lateinit var binding: ActivityWeatherNowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherNowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtraParams()
        setView()
        setViewAction()
        initViewModel()
        viewModel.getWeather(intentCity)
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

    private fun initViewModel() {
        viewModel.weatherData.observe(this, Observer {
            setViewByData(it)
        })
    }

    private fun setViewByData (data: WeatherResult) {
        binding.textTemperatureActual.text = data.list.first().main.temp.toString()
    }
}