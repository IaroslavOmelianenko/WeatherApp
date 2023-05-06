package com.github.iaroslavomelianenko.weatherapp.ui.fragments.info

import android.annotation.SuppressLint
import android.icu.number.NumberFormatter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.iaroslavomelianenko.weatherapp.R
import com.github.iaroslavomelianenko.weatherapp.databinding.CustomRowBinding
import com.github.iaroslavomelianenko.weatherapp.data.models.City
import java.text.DecimalFormat
import java.util.*
import kotlin.math.sign

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private lateinit var _binding: CustomRowBinding
    private var itemList = emptyList<City>()

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val _binding = CustomRowBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder{
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val currentItem = itemList[position]
        val temperatureMonth1 = currentItem.jun
        val temperatureMonth2 = currentItem.jul
        val temperatureMonth3 = currentItem.aug
        val averageTemperature = (temperatureMonth1 + temperatureMonth2 +temperatureMonth3) / 3
        val decimalFormat = DecimalFormat("+#;-#")

        holder._binding.tvCityId.text = currentItem.id.toString()
        holder._binding.tvCityName.text = currentItem.city
        holder._binding.tvCityTypeValueInRow.text = currentItem.cityType

        holder._binding.tvMonth1.text = "June"
        holder._binding.tvMonth2.text = "July"
        holder._binding.tvMonth3.text = "August"

        holder._binding.tvMonth1Value.text = decimalFormat.format(temperatureMonth1).toString()
        holder._binding.tvMonth2Value.text = decimalFormat.format(temperatureMonth2).toString()
        holder._binding.tvMonth3Value.text = decimalFormat.format(temperatureMonth3).toString()

        holder._binding.tvAverageSeasonTemperatureValue.text = decimalFormat.format(averageTemperature).toString()

        holder._binding.rowLayout.setOnClickListener {
            val action = CityTemperatureInfoFragmentDirections.actionCityTemperatureInfoFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(city: List<City>){
        this.itemList = city
        notifyDataSetChanged()
    }


}