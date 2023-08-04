package com.example.weather_forecast_test_repository.presentation.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.weather_forecast_test_repository.databinding.ActivityWeatherNowBinding
import androidx.lifecycle.Observer
import com.example.weather_forecast_test_repository.R
import com.example.weather_forecast_test_repository.dataclass.WeatherMainStat
import com.example.weather_forecast_test_repository.getTemperatureText
import com.example.weather_forecast_test_repository.presentation.forecast.ForecastActivity
import kotlin.math.roundToInt

class WeatherActivity: AppCompatActivity() {
    companion object {
        const val KEY_CITY_NAME = "city"
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
        intentCity = intent.getStringExtra(KEY_CITY_NAME)?:"Bangkok"
    }
    private fun setView(){
        binding.searchBar.layoutSearchEditText.setText(intentCity)
        binding.searchBar.layoutSearch.clearFocus()
        binding.textCity.maxLines = 1
        binding.textCity.setSingleLine()
        binding.textCity.ellipsize = TextUtils.TruncateAt.END
    }
    private fun setViewAction(){
        binding.buttonTempToggle.setOnClickListener {
            setTemperatureView(viewModel.weatherData.value!!)
        }
        binding.searchBar.buttonSearch.setOnClickListener {
            viewModel.getWeather(
                binding.searchBar.layoutSearchEditText.text.toString()
            )
        }
        binding.buttonFullDayForecast.setOnClickListener{
            val intent = ForecastActivity.getPage(
                this,
                binding.searchBar.layoutSearchEditText.text.toString()
            )
            startActivity(intent)
        }
    }

    private fun initViewModel() {
        viewModel.weatherData.observe(this, Observer {
            setTemperatureView(it)
            setHumidityView(it.humidity.roundToInt())
        })
        viewModel.cityName.observe(this, Observer{
            setCityName(it)
        })
    }

    private fun setTemperatureView (data: WeatherMainStat) {
        val state = binding.buttonTempToggle.isChecked
        binding.textTemperatureActual.text = data.temp.getTemperatureText(this, state)
        binding.textTemperatureFeeling.text = data.temp.getTemperatureText(this, state)
    }

    private fun setHumidityView (humid: Int) {
        binding.textHumidity.text = getString(
            R.string.text_humidity,
            humid
        )
    }

    private fun setCityName(city: String) {
        val metrics: DisplayMetrics = resources.displayMetrics
        var dp: Float = 20f - city.length.div(2)
        val fpixels: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, metrics);
        binding.textCity.textSize = fpixels
        binding.textCity.text = city
    }

}