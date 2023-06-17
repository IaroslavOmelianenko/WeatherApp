package com.github.iaroslavomelianenko.weatherapp.ui.fragments.info

import android.annotation.SuppressLint
import android.icu.number.NumberFormatter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.ListFragment
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.iaroslavomelianenko.weatherapp.R
import com.github.iaroslavomelianenko.weatherapp.databinding.CustomRowBinding
import com.github.iaroslavomelianenko.weatherapp.data.models.City
import com.github.iaroslavomelianenko.weatherapp.data.viewmodels.CityTemperatureInfoViewModel
import com.github.iaroslavomelianenko.weatherapp.utils.Season
import java.text.DecimalFormat
import java.util.*
import kotlin.math.sign

class ListAdapter(val cityTemperatureInfoViewModel: CityTemperatureInfoViewModel) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

//    private lateinit var _binding: CustomRowBinding
    private var selectedSeason = cityTemperatureInfoViewModel.getSeason()
    private var itemList = emptyList<City>()

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
        var averageTemperature = 0

        holder._binding.tvCityId.text = currentItem.id.toString()
        holder._binding.tvCityName.text = currentItem.city
        holder._binding.tvCityTypeValueInRow.text = currentItem.cityType

        fun changeSeason(
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


            holder._binding.tvMonth1Value.text = formatTemperature(temperatureMonth1)
            holder._binding.tvMonth2Value.text = formatTemperature(temperatureMonth2)
            holder._binding.tvMonth3Value.text = formatTemperature(temperatureMonth3)

            averageTemperature = (temperatureMonth1 + temperatureMonth2 + temperatureMonth3) / 3
        }

        when (selectedSeason) {
            Season.WINTER -> {
                changeSeason(
                    "Dec",
                    "Jan",
                    "Feb",
                    currentItem.dec,
                    currentItem.jan,
                    currentItem.feb
                )
            }

            Season.SPRING -> {
                changeSeason(
                    "Mar",
                    "Apr",
                    "May",
                    currentItem.mar,
                    currentItem.apr,
                    currentItem.may
                )
            }

            Season.SUMMER -> {
                changeSeason(
                    "Jun",
                    "Jul",
                    "Aug",
                    currentItem.jun,
                    currentItem.jul,
                    currentItem.aug
                )
            }

            else -> {
                changeSeason(
                    "Sep",
                    "Oct",
                    "Nov",
                    currentItem.sep,
                    currentItem.oct,
                    currentItem.nov
                )
            }
        }

        holder._binding.tvAverageSeasonTemperatureValue.text = formatTemperature(averageTemperature)

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

    private fun formatTemperature(temperature: Int): String {
        val decimalFormat = DecimalFormat("+#;-#")
        return if (temperature != 0) decimalFormat.format(temperature).toString()
        else temperature.toString()
    }
}