package com.example.weather_forecast_test_repository.presentation.forecast

import android.app.assist.AssistContent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.substring
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_forecast_test_repository.R
import com.example.weather_forecast_test_repository.databinding.ActivityForecastBinding
import com.example.weather_forecast_test_repository.dataclass.ForecastData
import com.example.weather_forecast_test_repository.dataclass.WeatherData
import com.example.weather_forecast_test_repository.getTemperatureText
import com.example.weather_forecast_test_repository.presentation.weather.WeatherActivity
import kotlin.math.roundToInt

class ForecastActivity: AppCompatActivity() {

    companion object {
        const val KEY_CITY_NAME = "city"
        fun getPage(context: Context, city: String): Intent {
            val intent =  Intent(context, ForecastActivity::class.java)
            intent.putExtra(KEY_CITY_NAME, city)
            return intent
        }
    }
    private val viewModel: ForecastViewModel by viewModel()
    private lateinit var binding: ActivityForecastBinding
    private lateinit var adapterForecast: ForecastAdapter
    private var intentCity: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtraParams()
        initAdapter()
        setView()
        setViewAction()
        initViewModel()

        viewModel.getForecast(intentCity)
    }

    private fun initAdapter() {
        adapterForecast = ForecastAdapter()
    }

    private fun getExtraParams() {
        intentCity = intent.getStringExtra(WeatherActivity.KEY_CITY_NAME)?:"Bangkok"
    }
    private fun setView(){
        binding.searchBar.layoutSearchEditText.setText(intentCity)
        binding.searchBar.layoutSearch.clearFocus()
        binding.textCityName.maxLines = 1
        binding.textCityName.setSingleLine()
        binding.textCityName.ellipsize = TextUtils.TruncateAt.END
        binding.recyclerForecast.apply {
            adapter = adapterForecast
            layoutManager = LinearLayoutManager(this@ForecastActivity)
            setHasFixedSize(true)
        }
    }

    private fun setViewAction() {
        binding.searchBar.buttonSearch.setOnClickListener {
            val city = binding.searchBar.layoutSearchEditText.text.toString()
            if(city.isEmpty()){
                binding.searchBar.layoutSearch.error = getString(R.string.error_blank_city_name)
            } else {
                binding.searchBar.layoutSearch.error = ""
                viewModel.getForecast(city)
            }
        }
        binding.buttonTempToggle.setOnClickListener {
            viewModel.forecastData.value?.let { data ->
                setForecastAdapter(data)
            }
        }
    }

    private fun initViewModel() {
        viewModel.forecastData.observe(this) {
            setForecastAdapter(it)
        }
        viewModel.cityData.observe(this) {
            setCityName(it)
        }
        viewModel.showError.observe(this){
            binding.searchBar.layoutSearch.error = getString(R.string.error_city_not_exist)
        }
        viewModel.unlockLayout.observe(this){
            binding.textCityName.visibility = View.VISIBLE
            binding.textForecastHeader.visibility = View.VISIBLE
            binding.textForecastSecondHeader.visibility = View.VISIBLE
            binding.textTempScale.visibility = View.VISIBLE
            binding.buttonTempToggle.visibility = View.VISIBLE
        }
    }

    private fun setCityName(city: String) {
        val metrics: DisplayMetrics = resources.displayMetrics
        var dp: Float = 20f - city.length.div(2)
        val fpixels: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, metrics);
        binding.textCityName.textSize = fpixels
        binding.textCityName.text = city
    }


    private fun setForecastAdapter (data: List<WeatherData>) {
        val state = binding.buttonTempToggle.isChecked
        var list = mutableListOf<ForecastData>()
        data.forEach{ data ->
            list.add(
                ForecastData(
                    time = data.dt_txt.replace(" ", "\n")
                        .substring(0, data.dt_txt.length - 3),
                    temp = data.main.temp.getTemperatureText(this, state),
                    feels_like = data.main.feels_like.getTemperatureText(this, state),
                    humidity = getString(
                        R.string.text_humidity,
                        data.main.humidity.roundToInt()
                    )
                )
            )
        }
        adapterForecast.dataList = list
    }
}