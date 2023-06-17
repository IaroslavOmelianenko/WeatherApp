package com.github.iaroslavomelianenko.weatherapp.ui.fragments.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.iaroslavomelianenko.weatherapp.R
import com.github.iaroslavomelianenko.weatherapp.data.viewmodels.CityTemperatureInfoViewModel
import com.github.iaroslavomelianenko.weatherapp.databinding.FragmentCityTemperatureInfoBinding
import com.github.iaroslavomelianenko.weatherapp.data.viewmodels.CityViewModel
import com.github.iaroslavomelianenko.weatherapp.utils.Season

class CityTemperatureInfoFragment : Fragment() {

    private lateinit var _binding: FragmentCityTemperatureInfoBinding
    private lateinit var cityViewModel: CityViewModel

    private val cityTemperatureInfoViewModel: CityTemperatureInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityTemperatureInfoBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        _binding.seasonsBtnGroup.addOnButtonCheckedListener { seasonsBtnGroup, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_winter -> {
                        cityTemperatureInfoViewModel.setSeason(Season.WINTER)
                    }
                    R.id.btn_spring -> {
                        cityTemperatureInfoViewModel.setSeason(Season.SPRING)
                    }
                    R.id.btn_summer -> {
                        cityTemperatureInfoViewModel.setSeason(Season.SUMMER)
                    }
                    R.id.btn_autumn -> {
                        cityTemperatureInfoViewModel.setSeason(Season.AUTUMN)
                    }
                }
            }
        }

        // Recyclerview
        val adapter = ListAdapter(cityTemperatureInfoViewModel)
        val recyclerView = _binding.rvCitiesTemperatureInfo
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CityViewModel
        cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        cityViewModel.readAllData.observe(viewLifecycleOwner, Observer { city ->
            adapter.setData(city)
        })

        // CityTemperatureInfoViewModel
        cityTemperatureInfoViewModel.season.observe(viewLifecycleOwner, Observer {season ->
            adapter.setSeason(season)
        })

        _binding.fabSettings.setOnClickListener {
            findNavController().navigate(R.id.action_cityTemperatureInfoFragment_to_settingsFragment)
        }
    }
}