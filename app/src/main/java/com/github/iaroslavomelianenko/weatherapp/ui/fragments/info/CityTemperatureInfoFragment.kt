package com.github.iaroslavomelianenko.weatherapp.ui.fragments.info

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
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
import com.github.iaroslavomelianenko.weatherapp.utils.TemperatureScale
import kotlinx.android.synthetic.main.fragment_city_temperature_info.*

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

        _binding.temperatureScaleBtnGroup.addOnButtonCheckedListener { temperatureBtnGroup, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_celsius -> {
                        cityTemperatureInfoViewModel.setTemperatureScale(TemperatureScale.CELSIUS)
                    }
                    R.id.btn_fahrenheit -> {
                        cityTemperatureInfoViewModel.setTemperatureScale(TemperatureScale.FAHRENHEIT)
                    }
                    R.id.btn_kelvin -> {
                        cityTemperatureInfoViewModel.setTemperatureScale(TemperatureScale.KELVIN)
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
        cityTemperatureInfoViewModel.season.observe(viewLifecycleOwner, Observer { season ->
            adapter.setSeason(season)
        })
        cityTemperatureInfoViewModel.temperatureScale.observe(
            viewLifecycleOwner
        ) { temperatureScale ->
            adapter.setTemperatureScale(temperatureScale)
        }

        _binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_cityTemperatureInfoFragment_to_settingsFragment)
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.menu_delete) {
                    deleteAllItems()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun deleteAllItems() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            cityViewModel.deleteAllCities()
            Toast.makeText(
                requireContext(),
                "Removed everything",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure want to delete everything?")
        builder.create().show()
    }
}