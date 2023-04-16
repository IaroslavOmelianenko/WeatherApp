package com.github.iaroslavomelianenko.weatherapp.ui.settings

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.iaroslavomelianenko.weatherapp.R
import com.github.iaroslavomelianenko.weatherapp.databinding.FragmentSettingsBinding
import com.github.iaroslavomelianenko.weatherapp.models.City
import com.github.iaroslavomelianenko.weatherapp.models.CityViewModel

class SettingsFragment : Fragment() {

    private lateinit var _binding: FragmentSettingsBinding
    private lateinit var cityViewModel: CityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        _binding.bAdd.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val city = _binding.etCity.text.toString()
        val cityType = _binding.etCityType.text.toString()
//        val month1 = Integer.parseInt(_binding.etMonth1.text.toString())
//        val month2 = Integer.parseInt(_binding.etMonth2.text.toString())
//        val month3 = Integer.parseInt(_binding.etMonth3.text.toString())//
        val month1 = _binding.etMonth1.text
        val month2 = _binding.etMonth2.text
        val month3 = _binding.etMonth3.text

        if (inputCheck(city, cityType, month1, month2, month3)) {
            //Create City object
            val city = City(
                0,
                city,
                cityType,
                0,
                0,
                0,
                0,
                0,
                Integer.parseInt(month1.toString()),
                Integer.parseInt(month2.toString()),
                Integer.parseInt(month3.toString()),
                0,
                0,
                0,
                0
            )
            //Add data to Database
            cityViewModel.addCity(city)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_settingsFragment_to_cityTemperatureInfoFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        city: String,
        cityType: String,
        month1: Editable,
        month2: Editable,
        month3: Editable
    ): Boolean {
        return !(
                TextUtils.isEmpty(city) &&
                        TextUtils.isEmpty(cityType) &&
                        month1.isEmpty() &&
                        month2.isEmpty() &&
                        month3.isEmpty()
                )
    }
}