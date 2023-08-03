package com.example.weather_forecast_test_repository.presentation.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.unit.dp
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.weather_forecast_test_repository.databinding.ActivityWeatherNowBinding
import androidx.lifecycle.Observer
import com.example.weather_forecast_test_repository.R
import com.example.weather_forecast_test_repository.dataclass.WeatherMainStat
import com.example.weather_forecast_test_repository.dataclass.WeatherResult
import kotlin.math.roundToInt

class WeatherActivity: AppCompatActivity() {
    companion object {
        private const val KEY_CITY_NAME = "city"
        fun getPage(context: Context, city: String): Intent{
            val intent =  Intent(context, WeatherActivity::class.java)
            intent.putExtra(KEY_CITY_NAME, city)
            return intent
        }
    }
    private val kelvinDiff: Double = 273.15
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
        binding.textTemperatureActual.text = getTemperatureText(data.temp)
        binding.textTemperatureFeeling.text = getTemperatureText(data.feels_like)
    }

    private fun setHumidityView (humid: Int) {
        binding.textHumidity.text = getString(
            R.string.text_humidity,
            humid
        )
    }

    private fun setCityName(city: String) {
        val metrics: DisplayMetrics = resources.displayMetrics
        var dp: Float = 20f
        if(city.length in 9..14) {
            dp = 15f
        } else if(city.length in 14..19){
            dp = 12f
        } else if (city.length > 19) {
            dp = 10f
        }
        val fpixels: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, metrics);
        binding.textCity.textSize = fpixels
        binding.textCity.text = city
    }

    private fun getTemperatureText (temp: Double): String {
        return if (binding.buttonTempToggle.isChecked){
            getString(
                R.string.text_temperature,
                toFahrenheit(temp),
                "F"
            )
        } else {
            getString(
                R.string.text_temperature,
                toCelsius(temp),
                "C"
            )
        }
    }

    fun toCelsius (temp: Double): String {
        return temp.minus(kelvinDiff).roundToInt().toString()
    }
    fun toFahrenheit (temp: Double):String {
        val tempF: Double = (1.80 * (temp - kelvinDiff)) + 32.00
        return tempF.roundToInt().toString()
    }

}