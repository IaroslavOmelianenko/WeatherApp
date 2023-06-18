package com.github.iaroslavomelianenko.weatherapp.ui.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.iaroslavomelianenko.weatherapp.R
import com.github.iaroslavomelianenko.weatherapp.data.models.City
import com.github.iaroslavomelianenko.weatherapp.data.viewmodels.CityViewModel
import com.github.iaroslavomelianenko.weatherapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var _binding: FragmentAddBinding
    private lateinit var cityViewModel: CityViewModel

    override fun onResume() {
        super.onResume()
        val cityTypes = resources.getStringArray(R.array.city_types)
        val cityTypesArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, cityTypes)
        _binding.actvCityType.setAdapter(cityTypesArrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater)
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
        val cityType = _binding.actvCityType.text.toString()
        val jan = emptyCheck(_binding.etJan.text)
        val feb = emptyCheck(_binding.etFeb.text)
        val mar = emptyCheck(_binding.etMar.text)
        val apr = emptyCheck(_binding.etApr.text)
        val may = emptyCheck(_binding.etMay.text)
        val jun = emptyCheck(_binding.etJun.text)
        val jul = emptyCheck(_binding.etJul.text)
        val aug = emptyCheck(_binding.etAug.text)
        val sep = emptyCheck(_binding.etSep.text)
        val oct = emptyCheck(_binding.etOct.text)
        val nov = emptyCheck(_binding.etNov.text)
        val dec = emptyCheck(_binding.etDec.text)

        if (inputCheck(city, cityType)
        ) {
            //Create City object
            val city = City(
                0,
                city,
                cityType,
                jan,
                feb,
                mar,
                apr,
                may,
                jun,
                jul,
                aug,
                sep,
                oct,
                nov,
                dec
            )

            //Add data to Database
            cityViewModel.addCity(city)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

            //Navigate back
            findNavController().navigate(R.id.action_settingsFragment_to_cityTemperatureInfoFragment)

        } else {
            Toast.makeText(
                requireContext(),
                "Enter city name and choose city type",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun inputCheck(city: String, cityType: String): Boolean {
        return !(TextUtils.isEmpty(city) || TextUtils.isEmpty(cityType))
    }

    private fun emptyCheck(value: Editable): Int {
        if (value.isNullOrBlank()) return 0
        else return Integer.parseInt(value.toString())
    }
}