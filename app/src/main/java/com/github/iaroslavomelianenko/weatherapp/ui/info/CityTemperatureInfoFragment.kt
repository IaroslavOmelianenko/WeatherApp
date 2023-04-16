package com.github.iaroslavomelianenko.weatherapp.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.iaroslavomelianenko.weatherapp.R
import com.github.iaroslavomelianenko.weatherapp.databinding.FragmentCityTemperatureInfoBinding
import com.github.iaroslavomelianenko.weatherapp.models.CityViewModel

class CityTemperatureInfoFragment : Fragment() {

    private lateinit var _binding: FragmentCityTemperatureInfoBinding
    private lateinit var cityViewModel: CityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityTemperatureInfoBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        _binding.fabSettings.setOnClickListener{
            findNavController().navigate(R.id.action_cityTemperatureInfoFragment_to_settingsFragment)
        }
    }
}