package com.github.iaroslavomelianenko.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.iaroslavomelianenko.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}