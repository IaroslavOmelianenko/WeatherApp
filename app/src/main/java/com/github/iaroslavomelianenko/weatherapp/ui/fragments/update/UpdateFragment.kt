package com.github.iaroslavomelianenko.weatherapp.ui.fragments.update

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
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.github.iaroslavomelianenko.weatherapp.R
import com.github.iaroslavomelianenko.weatherapp.data.models.City
import com.github.iaroslavomelianenko.weatherapp.data.viewmodels.CityViewModel
import com.github.iaroslavomelianenko.weatherapp.databinding.FragmentUpdateBinding
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {

    private lateinit var _binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mCityViewModel: CityViewModel

    override fun onResume() {
        super.onResume()
        val cityTypes = resources.getStringArray(R.array.city_types)
        val cityTypesArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, cityTypes)
        _binding.actvUpdateCityType.setAdapter(cityTypesArrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mCityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        _binding.etUpdateCity.setText(args.currentCity.city)
        _binding.actvUpdateCityType.setText(args.currentCity.cityType)
        _binding.etUpdateJan.setText(args.currentCity.jan.toString())
        _binding.etUpdateFeb.setText(args.currentCity.feb.toString())
        _binding.etUpdateMar.setText(args.currentCity.mar.toString())
        _binding.etUpdateApr.setText(args.currentCity.apr.toString())
        _binding.etUpdateMay.setText(args.currentCity.may.toString())
        _binding.etUpdateJun.setText(args.currentCity.jun.toString())
        _binding.etUpdateJul.setText(args.currentCity.jul.toString())
        _binding.etUpdateAug.setText(args.currentCity.aug.toString())
        _binding.etUpdateSep.setText(args.currentCity.sep.toString())
        _binding.etUpdateOct.setText(args.currentCity.oct.toString())
        _binding.etUpdateNov.setText(args.currentCity.nov.toString())
        _binding.etUpdateDec.setText(args.currentCity.dec.toString())

        _binding.bUpdate.setOnClickListener {
            updateItem()
        }
    }

    private fun updateItem() {
        val city = _binding.etUpdateCity.text.toString()
        val cityType = _binding.actvUpdateCityType.text.toString()
        val jan = emptyCheck(_binding.etUpdateJan.text)
        val feb = emptyCheck(_binding.etUpdateFeb.text)
        val mar = emptyCheck(_binding.etUpdateMar.text)
        val apr = emptyCheck(_binding.etUpdateApr.text)
        val may = emptyCheck(_binding.etUpdateMay.text)
        val jun = emptyCheck(_binding.etUpdateJun.text)
        val jul = emptyCheck(_binding.etUpdateJul.text)
        val aug = emptyCheck(_binding.etUpdateAug.text)
        val sep = emptyCheck(_binding.etUpdateSep.text)
        val oct = emptyCheck(_binding.etUpdateOct.text)
        val nov = emptyCheck(_binding.etUpdateNov.text)
        val dec = emptyCheck(_binding.etUpdateDec.text)

        if (inputCheck(city, cityType)
        ) {
            //Create updated city object
            val updatedCity = City(
                args.currentCity.id,
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

            //Update current city
            mCityViewModel.updateCity(updatedCity)
            Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()

            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_cityTemperatureInfoFragment)

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