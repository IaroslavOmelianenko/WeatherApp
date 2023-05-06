package com.github.iaroslavomelianenko.weatherapp.ui.fragments.update

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater)

        mCityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        _binding.etUpdateCity.setText(args.currentCity.city)
        _binding.etUpdateCityType.setText(args.currentCity.cityType)
        _binding.etUpdateMonth1.setText(args.currentCity.jun.toString())
        _binding.etUpdateMonth2.setText(args.currentCity.jul.toString())
        _binding.etUpdateMonth3.setText(args.currentCity.aug.toString())

        _binding.bUpdate.setOnClickListener {
            updateItem()
        }

        return _binding.root
    }

    private fun updateItem(){
        val city = etUpdateCity.text.toString()
        val cityType = etUpdateCityType.text.toString()
        val month1 = Integer.parseInt(etUpdateMonth1.text.toString())
        val month2 = Integer.parseInt(etUpdateMonth2.text.toString())
        val month3 = Integer.parseInt(etUpdateMonth3.text.toString())

        if(inputCheck(city, cityType, etUpdateMonth1.text, etUpdateMonth2.text, etUpdateMonth3.text)){
            //Create updated city object
            val updatedCity = City(args.currentCity.id, city, cityType,
            0,
            0,
            0,
            0,
            0,
            month1,
            month2,
            month3,
            0,
            0,
            0,
            0
            )

            //Update current city
            mCityViewModel.updateCity(updatedCity)
            Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()

            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_cityTemperatureInfoFragment)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
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