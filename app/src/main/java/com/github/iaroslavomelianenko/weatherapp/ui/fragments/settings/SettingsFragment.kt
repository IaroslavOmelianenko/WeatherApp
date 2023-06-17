package com.github.iaroslavomelianenko.weatherapp.ui.fragments.settings

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
import com.github.iaroslavomelianenko.weatherapp.data.models.City
import com.github.iaroslavomelianenko.weatherapp.data.viewmodels.CityViewModel

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
//        val month1 = _binding.etMonth1.text
//        val month2 = _binding.etMonth2.text
//        val month3 = _binding.etMonth3.text
        val jan = _binding.etJan.text
        val feb = _binding.etFeb.text
        val mar = _binding.etMar.text
        val apr = _binding.etApr.text
        val may = _binding.etMay.text
        val jun = _binding.etJun.text
        val jul = _binding.etJul.text
        val aug = _binding.etAug.text
        val sep = _binding.etSep.text
        val oct = _binding.etOct.text
        val nov = _binding.etNov.text
        val dec = _binding.etDec.text


        if (inputCheck(
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
        ) {
            //Create City object
            val city = City(
                0,
                city,
                cityType,
                Integer.parseInt(jan.toString()),
                Integer.parseInt(feb.toString()),
                Integer.parseInt(mar.toString()),
                Integer.parseInt(apr.toString()),
                Integer.parseInt(may.toString()),
                Integer.parseInt(jun.toString()),
                Integer.parseInt(jul.toString()),
                Integer.parseInt(aug.toString()),
                Integer.parseInt(sep.toString()),
                Integer.parseInt(oct.toString()),
                Integer.parseInt(nov.toString()),
                Integer.parseInt(dec.toString()),
            )
            //Add data to Database
            cityViewModel.addCity(city)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_settingsFragment_to_cityTemperatureInfoFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "Please fill out at least one season",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun inputCheck(
        city: String,
        cityType: String,
        jan: Editable,
        feb: Editable,
        mar: Editable,
        apr: Editable,
        may: Editable,
        jun: Editable,
        jul: Editable,
        aug: Editable,
        sep: Editable,
        oct: Editable,
        nov: Editable,
        dec: Editable,
    ): Boolean {
        return !(
                TextUtils.isEmpty(city) &&
                        TextUtils.isEmpty(cityType) &&
                        ((dec.isEmpty() && jan.isEmpty() && feb.isEmpty()) ||
                                (mar.isEmpty() && apr.isEmpty() && may.isEmpty()) ||
                                (jun.isEmpty() && jul.isEmpty() && aug.isEmpty()) ||
                                (sep.isEmpty() && oct.isEmpty() && nov.isEmpty()))
                )
    }
}