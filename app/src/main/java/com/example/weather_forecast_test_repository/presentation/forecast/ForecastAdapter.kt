package com.example.weather_forecast_test_repository.presentation.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather_forecast_test_repository.databinding.LayoutForecastDataBinding
import com.example.weather_forecast_test_repository.dataclass.ForecastData
import com.example.weather_forecast_test_repository.dataclass.WeatherData
import com.example.weather_forecast_test_repository.getTemperatureText
import kotlin.properties.Delegates

class ForecastAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList: List<ForecastData>? by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutForecastDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataList?.let {
            it[position]
        }?.let {
            (holder as ViewHolder).bind(
                it
            )
        }
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    inner class ViewHolder(private val binding: LayoutForecastDataBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (data: ForecastData) {
            binding.textForecastTime.text = data.time
            binding.textActualTemp.text = data.temp
            binding.textFeelingTemp.text = data.feels_like
            binding.textHumid.text = data.humidity
        }
    }

}