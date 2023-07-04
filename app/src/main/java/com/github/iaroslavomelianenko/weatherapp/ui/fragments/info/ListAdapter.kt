package com.github.iaroslavomelianenko.weatherapp.ui.fragments.info

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.iaroslavomelianenko.weatherapp.R
import com.github.iaroslavomelianenko.weatherapp.databinding.CustomRowBinding
import com.github.iaroslavomelianenko.weatherapp.data.models.City
import com.github.iaroslavomelianenko.weatherapp.data.viewmodels.CityTemperatureInfoViewModel
import com.github.iaroslavomelianenko.weatherapp.utils.Season
import com.github.iaroslavomelianenko.weatherapp.utils.TemperatureScale
import java.text.DecimalFormat


class ListAdapter(val cityTemperatureInfoViewModel: CityTemperatureInfoViewModel) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var selectedSeason = cityTemperatureInfoViewModel.getSeason()
    private var selectedTemperatureScale = cityTemperatureInfoViewModel.getTemperatureScale()
    private var itemList = emptyList<City>()
    val decimalFormat = DecimalFormat("+#;-#")

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val _binding = CustomRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val currentItem = itemList[position]
        val positionIndex = position + 1

        holder._binding.tvCityId.text = positionIndex.toString()
        holder._binding.tvCityName.text = currentItem.city
        holder._binding.tvCityTypeValueInRow.text = currentItem.cityType

        fun changeValues(
            month1Name: String,
            month2Name: String,
            month3Name: String,
            temperatureMonth1: Int,
            temperatureMonth2: Int,
            temperatureMonth3: Int,
        ) {
            holder._binding.tvMonth1.text = month1Name
            holder._binding.tvMonth2.text = month2Name
            holder._binding.tvMonth3.text = month3Name

            val averageTemperature = (temperatureMonth1 + temperatureMonth2 + temperatureMonth3) / 3

            when (selectedTemperatureScale) {
                TemperatureScale.FAHRENHEIT -> {
                    holder._binding.tvMonth1Value.text = formatTemperatureFahrenheit(temperatureMonth1)
                    holder._binding.tvMonth2Value.text = formatTemperatureFahrenheit(temperatureMonth2)
                    holder._binding.tvMonth3Value.text = formatTemperatureFahrenheit(temperatureMonth3)
                    holder._binding.tvAverageSeasonTemperatureValue.text = formatTemperatureFahrenheit(averageTemperature)
                }

                TemperatureScale.KELVIN -> {
                    holder._binding.tvMonth1Value.text = formatTemperatureKelvin(temperatureMonth1)
                    holder._binding.tvMonth2Value.text = formatTemperatureKelvin(temperatureMonth2)
                    holder._binding.tvMonth3Value.text = formatTemperatureKelvin(temperatureMonth3)
                    holder._binding.tvAverageSeasonTemperatureValue.text = formatTemperatureKelvin(averageTemperature)
                }
                else -> {
                    holder._binding.tvMonth1Value.text = formatTemperatureCelsius(temperatureMonth1)
                    holder._binding.tvMonth2Value.text = formatTemperatureCelsius(temperatureMonth2)
                    holder._binding.tvMonth3Value.text = formatTemperatureCelsius(temperatureMonth3)
                    holder._binding.tvAverageSeasonTemperatureValue.text = formatTemperatureCelsius(averageTemperature)
                }
            }
        }

        when (selectedSeason) {
            Season.WINTER -> {
                changeValues(
                    "Dec",
                    "Jan",
                    "Feb",
                    currentItem.dec,
                    currentItem.jan,
                    currentItem.feb
                )
            }

            Season.SPRING -> {
                changeValues(
                    "Mar",
                    "Apr",
                    "May",
                    currentItem.mar,
                    currentItem.apr,
                    currentItem.may
                )
            }

            Season.SUMMER -> {
                changeValues(
                    "Jun",
                    "Jul",
                    "Aug",
                    currentItem.jun,
                    currentItem.jul,
                    currentItem.aug
                )
            }

            else -> {
                changeValues(
                    "Sep",
                    "Oct",
                    "Nov",
                    currentItem.sep,
                    currentItem.oct,
                    currentItem.nov
                )
            }
        }

        holder._binding.rowLayout.setOnClickListener {
            val action =
                CityTemperatureInfoFragmentDirections.actionCityTemperatureInfoFragmentToUpdateFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(city: List<City>) {
        this.itemList = city
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSeason(season: Season) {
        this.selectedSeason = season
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTemperatureScale(temperatureScale: TemperatureScale) {
        this.selectedTemperatureScale = temperatureScale
        notifyDataSetChanged()
    }


    private fun formatTemperatureCelsius(temperature: Int): String {
        return if (temperature != 0) "${decimalFormat.format(temperature)}°C"
        else "${temperature}°C"
    }

    private fun formatTemperatureFahrenheit(temperature: Int): String {
        val fahrenheitTemperature = (temperature * 9 / 5) + 32
        return if (fahrenheitTemperature != 0) "${decimalFormat.format(fahrenheitTemperature)}°F"
        else "${fahrenheitTemperature}°F"
    }

    private fun formatTemperatureKelvin(temperature: Int): String {
        val kelvinTemperature = (temperature + 273.15).toInt()
        return "${kelvinTemperature}K"
    }

}